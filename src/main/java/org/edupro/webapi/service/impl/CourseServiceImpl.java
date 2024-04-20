package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.CourseEntity;
import org.edupro.webapi.model.request.CourseReq;
import org.edupro.webapi.model.response.CourseRes;
import org.edupro.webapi.repository.CourseRepo;
import org.edupro.webapi.service.CourseService;
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
public class CourseServiceImpl implements CourseService {
    private final CourseRepo repo;

    @Override
    public List<CourseRes> get() {
        List<CourseEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        CourseEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        /*
        if(repo.existsByKode(request.getKode())){
            log.info("Save Course gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

         */

        CourseEntity result = this.convertReqToEntity(request);
        result.setId(UUID.randomUUID().toString().toUpperCase());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<CourseRes> saveOrUpdate(CourseEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Course, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Course gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private CourseEntity getEntityById(String id) {
        CourseEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CourseRes convertEntityToRes(CourseEntity entity){
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private CourseEntity convertReqToEntity(CourseReq request){
        CourseEntity result = new CourseEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(CourseReq request, CourseEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}