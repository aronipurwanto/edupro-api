package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.KelasMapelEntity;
import org.edupro.webapi.model.request.KelasMapelReq;
import org.edupro.webapi.model.response.KelasMapelRes;
import org.edupro.webapi.repository.KelasMapelRepo;
import org.edupro.webapi.repository.MapelRepo;
import org.edupro.webapi.service.KelasMapelService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KelasMapelServiceImpl implements KelasMapelService {
    private final KelasMapelRepo repo;
    private final MapelRepo mapelRepo;

    @Override
    public List<KelasMapelRes> get() {
        List<KelasMapelEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<KelasMapelRes> getById(String id) {
        KelasMapelEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<KelasMapelRes> save(KelasMapelReq request) {
        if(repo.existsByKelasIdAndMapelId(request.getKelasId(), request.getMapelId())){
            Map<String, String> errors = Map.of("kode", "kelas "+ request.getKelasId()+" dan mapel "+ request.getMapelId() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        KelasMapelEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasMapelRes> update(KelasMapelReq request, String id) {
        KelasMapelEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasMapelRes> delete(String id) {
        KelasMapelEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    private Optional<KelasMapelRes> saveOrUpdate(KelasMapelEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelompok Mapel, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Kelompok Mapel gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private KelasMapelEntity getEntityById(String id) {
        KelasMapelEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private KelasMapelRes convertEntityToRes(KelasMapelEntity entity){
        KelasMapelRes result = new KelasMapelRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private KelasMapelEntity convertReqToEntity(KelasMapelReq request){
        KelasMapelEntity result = new KelasMapelEntity();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private void convertReqToEntity(KelasMapelReq request, KelasMapelEntity result){
        BeanUtils.copyProperties(request, result);
    }
}
