package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.AttachmentEntity;
import org.edupro.webapi.model.request.AttachmentReq;
import org.edupro.webapi.model.response.AttachmentRes;
import org.edupro.webapi.repository.AttachmentRepo;
import org.edupro.webapi.service.AttachmentService;
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
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepo repo;

    @Override
    public List<AttachmentRes> get() {
        List<AttachmentEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<AttachmentRes> getById(String id) {
        AttachmentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<AttachmentRes> save(AttachmentReq request) {
        /*
        if(repo.existsByKode(request.getKode())){
            log.info("Save Attachment gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

         */

        AttachmentEntity result = this.convertReqToEntity(request);
        result.setId(UUID.randomUUID().toString().toUpperCase());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<AttachmentRes> update(AttachmentReq request, String id) {
        AttachmentEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<AttachmentRes> delete(String id) {
        AttachmentEntity result = this.getEntityById(id);
        return saveOrUpdate(result);
    }

    private Optional<AttachmentRes> saveOrUpdate(AttachmentEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Attachment, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Attachment gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private AttachmentEntity getEntityById(String id) {
        AttachmentEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private AttachmentRes convertEntityToRes(AttachmentEntity entity){
        AttachmentRes result = new AttachmentRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private AttachmentEntity convertReqToEntity(AttachmentReq request){
        AttachmentEntity result = new AttachmentEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(AttachmentReq request, AttachmentEntity result){
        BeanUtils.copyProperties(request, result);
    }
}
