package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.NilaiSiswaEntity;
import org.edupro.webapi.model.request.NilaiSiswaReq;
import org.edupro.webapi.model.response.NilaiSiswaRes;
import org.edupro.webapi.repository.NilaiSiswaRepo;
import org.edupro.webapi.service.NilaiSiswaService;
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
public class NilaiSiswaServiceImpl implements NilaiSiswaService {
    private final NilaiSiswaRepo repo;

    @Override
    public List<NilaiSiswaRes> get() {
        List<NilaiSiswaEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<NilaiSiswaRes> getById(String id) {
        NilaiSiswaEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<NilaiSiswaRes> save(NilaiSiswaReq request) {
        NilaiSiswaEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<NilaiSiswaRes> update(NilaiSiswaReq request, String id) {
        NilaiSiswaEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<NilaiSiswaRes> delete(String id) {
        NilaiSiswaEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<NilaiSiswaRes> saveOrUpdate(NilaiSiswaEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save NilaiSiswa, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save NilaiSiswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private NilaiSiswaEntity getEntityById(String id) {
        NilaiSiswaEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private NilaiSiswaRes convertEntityToRes(NilaiSiswaEntity entity){
        NilaiSiswaRes result = new NilaiSiswaRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private NilaiSiswaEntity convertReqToEntity(NilaiSiswaReq request){
        NilaiSiswaEntity result = new NilaiSiswaEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(NilaiSiswaReq request, NilaiSiswaEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
