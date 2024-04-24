package org.edupro.webapi.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.LevelEntity;
import org.edupro.webapi.model.request.CommonLembagaReq;
import org.edupro.webapi.model.response.CommonLembagaRes;
import org.edupro.webapi.repository.LevelRepo;
import org.edupro.webapi.service.LevelService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepo repo;

    @Override
    public List<CommonLembagaRes> get() {
        List<LevelEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CommonLembagaRes> getById(String id) {
        LevelEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CommonLembagaRes> save(CommonLembagaReq request) {
        if(repo.existsByIdLembagaAndKode(request.getIdLembaga(),request.getKode())){
            Map<String, String> errors = Map.of("kode", "Id Lembaga "+ request.getIdLembaga()+" dan Kode "+ request.getKode() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        LevelEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CommonLembagaRes> update(CommonLembagaReq request, String id) {
        LevelEntity result = this.getEntityById(id);
        convertReqToEntity(request, result);
        result.setId(id);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<CommonLembagaRes> delete(String id) {
        LevelEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<CommonLembagaRes> saveOrUpdate(LevelEntity result) {
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

    private CommonLembagaRes convertEntityToRes(LevelEntity entity){
        CommonLembagaRes result = new CommonLembagaRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private LevelEntity convertReqToEntity(CommonLembagaReq request){
        LevelEntity result = new LevelEntity();
        BeanUtils.copyProperties(request, result);
        result.setId(UUID.randomUUID().toString().toUpperCase());
        result.setStatus(DataStatus.AKTIF);

        return result;
    }

    private void convertReqToEntity(CommonLembagaReq request, LevelEntity result){
        BeanUtils.copyProperties(request, result);
    }
}
