package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.SesiAkademikEntity;
import org.edupro.webapi.model.entity.SesiAkademikId;
import org.edupro.webapi.model.request.SesiAkademikReq;
import org.edupro.webapi.model.response.SesiAkademikRes;
import org.edupro.webapi.repository.SesiAkademikRepo;
import org.edupro.webapi.service.SesiService;
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
public class SesiServiceImpl implements SesiService {
    private final SesiAkademikRepo repo;

    @Override
    public List<SesiAkademikRes> get() {
        List<SesiAkademikEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<SesiAkademikRes> getById(Integer id, Integer urut) {
        SesiAkademikEntity result = this.getEntityById(new SesiAkademikId(id, urut));

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<SesiAkademikRes> save(SesiAkademikReq request) {
        var id = new SesiAkademikId(request.getTahunPelajaran(),request.getUrut());
        if(repo.existsById(id)){
            Map<String, String> errors = Map.of("kode", "Id Lembaga "+ request.getTahunPelajaran()+" dan Kode "+ request.getUrut() +" sudah digunakan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        SesiAkademikEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<SesiAkademikRes> update(SesiAkademikReq request, Integer id, Integer urut) {
        SesiAkademikEntity result = this.getEntityById(new SesiAkademikId(id, urut));

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    public Optional<SesiAkademikRes> saveOrUpdate(SesiAkademikEntity result) {
        try{
            this.repo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Sesi gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save SesiAkademik gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public Optional<SesiAkademikRes> delete(Integer id, Integer urut) {
        SesiAkademikEntity result = this.getEntityById(new SesiAkademikId(id, urut));
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private SesiAkademikEntity getEntityById(SesiAkademikId SesiAkademikId) {
        SesiAkademikEntity result = this.repo.findById(SesiAkademikId).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Id Lembaga "+ SesiAkademikId.getTahunPelajaran()+" dan Kode "+ SesiAkademikId.getUrut() +" tidak ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private SesiAkademikRes convertEntityToRes(SesiAkademikEntity entity){
        SesiAkademikRes result = new SesiAkademikRes();
        BeanUtils.copyProperties(entity, result);
        result.setTahunPelajaran(entity.getId().getTahunPelajaran());
        result.setUrut(entity.getId().getUrut());
        result.setStatus(entity.getStatus());
        return result;
    }

    private SesiAkademikEntity convertReqToEntity(SesiAkademikReq request){
        SesiAkademikId id = new SesiAkademikId(request.getTahunPelajaran(), request.getUrut());
        return new SesiAkademikEntity(id,request.getKodeKurikulum(), DataStatus.AKTIF);
    }

    private void convertReqToEntity(SesiAkademikReq request, SesiAkademikEntity result){
        result.setKodeKurikulum(request.getKodeKurikulum());
    }
}
