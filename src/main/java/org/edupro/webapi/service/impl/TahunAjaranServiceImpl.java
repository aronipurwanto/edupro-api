package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.TahunAjaranEntity;
import org.edupro.webapi.model.request.TahunAjaranReq;
import org.edupro.webapi.model.response.TahunAjaranRes;
import org.edupro.webapi.repository.TahunAjaranRepo;
import org.edupro.webapi.service.TahunAjaranService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TahunAjaranServiceImpl implements TahunAjaranService {
    private final TahunAjaranRepo repo;

    @Override
    public List<TahunAjaranRes> get() {
        List<TahunAjaranEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<TahunAjaranRes> getById(String id) {
        TahunAjaranEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<TahunAjaranRes> save(TahunAjaranReq request) {
        if(repo.existsByNama(request.getNama())){
            Map<String, String> errors = Map.of("nama", "Nama "+ request.getNama()+" sudah digunakan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        TahunAjaranEntity result = this.convertReqToEntity(request);
        result.setId(UUID.randomUUID().toString().toUpperCase());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<TahunAjaranRes> update(TahunAjaranReq request, String id) {
        TahunAjaranEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<TahunAjaranRes> delete(String id) {
        TahunAjaranEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    public Optional<TahunAjaranRes> saveOrUpdate(TahunAjaranEntity entity) {
        try{
            this.repo.save(entity);
            return Optional.of(this.convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save TahunAjaran gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save TahunAjaran gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private TahunAjaranEntity getEntityById(String id) {
        TahunAjaranEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private TahunAjaranRes convertEntityToRes(TahunAjaranEntity entity){
        TahunAjaranRes result = new TahunAjaranRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private TahunAjaranEntity convertReqToEntity(TahunAjaranReq request){
        TahunAjaranEntity result = new TahunAjaranEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.AKTIF);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(TahunAjaranReq request, TahunAjaranEntity result){
        result.setNama(request.getNama());
        result.setKodeKurikulum(request.getKodeKurikulum());
    }
}
