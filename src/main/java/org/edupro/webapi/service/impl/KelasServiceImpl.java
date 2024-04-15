package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.KelasEntity;
import org.edupro.webapi.model.entity.KelasId;
import org.edupro.webapi.model.request.KelasReq;
import org.edupro.webapi.model.response.KelasRes;
import org.edupro.webapi.repository.KelasRepo;
import org.edupro.webapi.service.KelasService;
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
public class KelasServiceImpl implements KelasService {
    private final KelasRepo repo;

    @Override
    public List<KelasRes> get() {
        List<KelasEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<KelasRes> getById(KelasId id) {
        KelasEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<KelasRes> save(KelasReq request) {
        var id = this.convertReqToId(request);
        if(repo.existsById(id)){
            log.info("Save Kelas gagal, terjadi error : id sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        KelasEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasRes> update(KelasReq request) {
        var id = this.convertReqToId(request);
        KelasEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasRes> delete(KelasId id) {
        KelasEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<KelasRes> saveOrUpdate(KelasEntity result) {
        try{
            this.repo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelas, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Kelas gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private KelasEntity getEntityById(KelasId id) {
        KelasEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private KelasId convertReqToId(KelasReq request) {
        KelasId result = new KelasId(request.getIdLembaga(), request.getTahunAjaranId(), request.getKode());
        return result;
    }

    private KelasRes convertEntityToRes(KelasEntity entity){
        KelasRes result = new KelasRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private KelasEntity convertReqToEntity(KelasReq request){
        KelasEntity result = new KelasEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(KelasReq request, KelasEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
