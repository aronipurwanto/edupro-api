package org.edupro.webapi.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.LookupEntity;
import org.edupro.webapi.model.request.LookupReq;
import org.edupro.webapi.model.response.LookupRes;
import org.edupro.webapi.repository.LookupRepo;
import org.edupro.webapi.service.LookupService;
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
public class LookupServiceImpl implements LookupService {
    private final LookupRepo repo;

    @Override
    public List<LookupRes> get() {
        List<LookupEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<LookupRes> getById(Integer id) {
        LookupEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<LookupRes> save(LookupReq request) {
        if(repo.existsByGroupAndKode(request.getGroup(), request.getKode())){
            Map<String, String> errors = Map.of("kode", "Group "+ request.getGroup() +" dan Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        LookupEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<LookupRes> update(LookupReq request, Integer id) {
        LookupEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<LookupRes> delete(Integer id) {
        LookupEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    private Optional<LookupRes> saveOrUpdate(LookupEntity result) {
        try{
            this.repo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Lookup, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Lookup gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private LookupEntity getEntityById(Integer id) {
        LookupEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private LookupRes convertEntityToRes(LookupEntity entity){
        LookupRes result = new LookupRes();

        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private LookupEntity convertReqToEntity(LookupReq request){
        LookupEntity result = new LookupEntity();

        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(LookupReq request, LookupEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
