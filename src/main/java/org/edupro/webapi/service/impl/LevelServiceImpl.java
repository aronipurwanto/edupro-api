package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.LevelEntity;
import org.edupro.webapi.model.request.LevelReq;
import org.edupro.webapi.model.response.LevelRes;
import org.edupro.webapi.repository.LevelRepo;
import org.edupro.webapi.service.LevelService;
import org.edupro.webapi.util.CommonUtil;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepo repo;

    @Override
    public List<LevelRes> get() {
        List<LevelEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<LevelRes> getById(String id) {
        LevelEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<LevelRes> save(LevelReq request) {
        if(repo.existsByIdLembagaAndKode(request.getIdLembaga(),request.getKode())){
            Map<String, String> errors = Map.of("kode", "Id Lembaga "+ request.getIdLembaga()+" dan Kode "+ request.getKode() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        LevelEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<LevelRes> update(LevelReq request, String id) {
        LevelEntity result = this.getEntityById(id);
        convertReqToEntity(request, result);
        result.setId(id);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<LevelRes> delete(String id) {
        LevelEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<LevelRes> saveOrUpdate(LevelEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Level, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Level gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private LevelEntity getEntityById(String id) {
        LevelEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private LevelRes convertEntityToRes(LevelEntity entity){
        LevelRes result = new LevelRes();
        BeanUtils.copyProperties(entity, result);

        if (entity.getLembaga() != null){
            result.setNamaLembaga(entity.getLembaga().getNama());
        }

        return result;
    }

    private LevelEntity convertReqToEntity(LevelReq request){
        LevelEntity result = new LevelEntity();
        BeanUtils.copyProperties(request, result);
        result.setId(CommonUtil.getUUID());
        result.setStatus(DataStatus.AKTIF);

        return result;
    }

    private void convertReqToEntity(LevelReq request, LevelEntity result){
        BeanUtils.copyProperties(request, result);
    }
}
