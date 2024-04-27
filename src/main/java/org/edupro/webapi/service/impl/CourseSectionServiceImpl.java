package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.CourseSectionEntity;
import org.edupro.webapi.model.request.CourseSectionReq;
import org.edupro.webapi.model.response.CourseSectionRes;
import org.edupro.webapi.repository.CourseSectionRepo;
import org.edupro.webapi.service.CourseSectionService;
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
public class CourseSectionServiceImpl implements CourseSectionService {
    private final CourseSectionRepo repo;

    @Override
    public List<CourseSectionRes> get() {
        List<CourseSectionEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseSectionRes> getById(String id) {
        CourseSectionEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CourseSectionRes> save(CourseSectionReq request) {
        /*
        if(repo.existsByKode(request.getKode())){
            log.info("Save CourseSection gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

         */

        CourseSectionEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseSectionRes> update(CourseSectionReq request, String id) {
        CourseSectionEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseSectionRes> delete(String id) {
        CourseSectionEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<CourseSectionRes> saveOrUpdate(CourseSectionEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save CourseSection, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save CourseSection gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private CourseSectionEntity getEntityById(String id) {
        CourseSectionEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CourseSectionRes convertEntityToRes(CourseSectionEntity entity){
        CourseSectionRes result = new CourseSectionRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private CourseSectionEntity convertReqToEntity(CourseSectionReq request){
        CourseSectionEntity result = new CourseSectionEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(CourseSectionReq request, CourseSectionEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
