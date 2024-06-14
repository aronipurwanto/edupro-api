package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.StudentEntity;
import org.edupro.webapi.model.request.SiswaReq;
import org.edupro.webapi.model.response.SiswaRes;
import org.edupro.webapi.repository.SiswaRepo;
import org.edupro.webapi.service.BaseService;
import org.edupro.webapi.service.SiswaService;
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
public class SiswaServiceImpl extends BaseService implements SiswaService {
    private final SiswaRepo repo;

    @Override
    public List<SiswaRes> get() {
        List<StudentEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<SiswaRes> getById(String id) {
        StudentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<SiswaRes> save(SiswaReq request) {
        if(repo.existsByNisn(request.getNisn())){
            Map<String, String> errors = Map.of("nisn", "NISN "+ request.getNisn()+" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        StudentEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<SiswaRes> update(SiswaReq request, String id) {
        StudentEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<SiswaRes> delete(String id) {
        StudentEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    public Optional<SiswaRes> saveOrUpdate(StudentEntity entity) {
        try{
            this.repo.saveAndFlush(entity);
            return Optional.of(this.convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save Siswa gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Siswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private StudentEntity getEntityById(String id) {
        StudentEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private SiswaRes convertEntityToRes(StudentEntity entity){
        SiswaRes result = new SiswaRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private StudentEntity convertReqToEntity(SiswaReq request){
        StudentEntity result = new StudentEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.AKTIF);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(SiswaReq request, StudentEntity result){
        result.setName(request.getNama());
        result.setNisn(request.getNisn());
        result.setDob(request.getTanggalLahir());
        result.setPob(request.getKotaTempatLahir());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
