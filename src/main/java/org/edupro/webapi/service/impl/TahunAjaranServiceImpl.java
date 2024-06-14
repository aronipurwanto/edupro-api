package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.AcademicYearEntity;
import org.edupro.webapi.model.request.TahunAjaranReq;
import org.edupro.webapi.model.response.TahunAjaranRes;
import org.edupro.webapi.repository.TahunAjaranRepo;
import org.edupro.webapi.service.BaseService;
import org.edupro.webapi.service.TahunAjaranService;
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
public class TahunAjaranServiceImpl extends BaseService implements TahunAjaranService {
    private final TahunAjaranRepo repo;

    @Override
    public List<TahunAjaranRes> get() {
        List<AcademicYearEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public List<TahunAjaranRes> getByKurikulumId(String kurikulumId) {
        List<AcademicYearEntity> result = this.repo.findAllByKurikulumId(kurikulumId);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<TahunAjaranRes> getById(String id) {
        AcademicYearEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<TahunAjaranRes> save(TahunAjaranReq request) {
        if(repo.existsByNama(request.getNama())){
            Map<String, String> errors = Map.of("nama", "Nama "+ request.getNama()+" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicYearEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<TahunAjaranRes> update(TahunAjaranReq request, String id) {
        AcademicYearEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<TahunAjaranRes> delete(String id) {
        AcademicYearEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    public Optional<TahunAjaranRes> saveOrUpdate(AcademicYearEntity entity) {
        try{
            this.repo.saveAndFlush(entity);
            return Optional.of(this.convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save TahunAjaran gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save TahunAjaran gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private AcademicYearEntity getEntityById(String id) {
        AcademicYearEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private TahunAjaranRes convertEntityToRes(AcademicYearEntity entity){
        TahunAjaranRes result = new TahunAjaranRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private AcademicYearEntity convertReqToEntity(TahunAjaranReq request){
        AcademicYearEntity result = new AcademicYearEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.AKTIF);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(TahunAjaranReq request, AcademicYearEntity result){
        result.setName(request.getNama());
        result.setKodeKurikulum(request.getKodeKurikulum());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
