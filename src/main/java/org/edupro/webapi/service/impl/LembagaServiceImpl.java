package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.LembagaEntity;
import org.edupro.webapi.model.request.LembagaReq;
import org.edupro.webapi.model.response.LembagaRes;
import org.edupro.webapi.repository.LembagaRepo;
import org.edupro.webapi.service.LembagaService;
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
public class LembagaServiceImpl implements LembagaService {
    private final LembagaRepo repo;

    @Override
    public List<LembagaRes> get() {
        List<LembagaEntity> result = this.repo.findAll();
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<LembagaRes> getById(Integer id) {
        LembagaEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<LembagaRes> save(LembagaReq request) {
        LembagaEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<LembagaRes> update(LembagaReq request, Integer id) {
        LembagaEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<LembagaRes> delete(Integer id) {
        LembagaEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    private Optional<LembagaRes> saveOrUpdate(LembagaEntity result) {
        try{
            this.repo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Lembaga, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Lembaga gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private LembagaRes convertEntityToRes(LembagaEntity entity){
        LembagaRes result = new LembagaRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private LembagaEntity convertReqToEntity(LembagaReq request){
        LembagaEntity result = new LembagaEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(LembagaReq request, LembagaEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
