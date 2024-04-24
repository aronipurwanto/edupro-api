package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.KurikulumEntity;
import org.edupro.webapi.model.entity.SesiAkademikEntity;
import org.edupro.webapi.model.entity.TahunAjaranEntity;
import org.edupro.webapi.model.request.SesiAkademikReq;
import org.edupro.webapi.model.response.SesiAkademikRes;
import org.edupro.webapi.repository.KurikulumRepo;
import org.edupro.webapi.repository.SesiAkademikRepo;
import org.edupro.webapi.repository.TahunAjaranRepo;
import org.edupro.webapi.service.SesiAkademikService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SesiAkademikServiceImpl implements SesiAkademikService {
    private final SesiAkademikRepo repo;
    private final KurikulumRepo kurikulumRepo;
    private final TahunAjaranRepo tahunAjaranRepo;

    @Override
    public List<SesiAkademikRes> get() {
        List<SesiAkademikEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<SesiAkademikRes> getById(String id) {
        SesiAkademikEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<SesiAkademikRes> save(SesiAkademikReq request) {
        if(repo.existsByKurikulumIdAndTahunAjaranIdAndUrut(request.getKurikulumId(), request.getTahunAjaranId(), request.getUrut())){
            Map<String, String> errors = Map.of("tahunAjaranId", "kurikulumId "+ request.getKurikulumId()+" dan tahunAjaranId "+ request.getTahunAjaranId() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        SesiAkademikEntity result = this.convertReqToEntity(request);
        result.setId(UUID.randomUUID().toString().toUpperCase());

        return saveOrUpdate(result);
    }

    @Override
    public Optional<SesiAkademikRes> update(SesiAkademikReq request, String id) {
        SesiAkademikEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        result.setId(id);

        return saveOrUpdate(result);
    }

    public Optional<SesiAkademikRes> saveOrUpdate(SesiAkademikEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Sesi gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save SesiAkademik gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public Optional<SesiAkademikRes> delete(String id) {
        SesiAkademikEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private SesiAkademikEntity getEntityById(String id) {
        SesiAkademikEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private SesiAkademikRes convertEntityToRes(SesiAkademikEntity entity){
        SesiAkademikRes result = new SesiAkademikRes();
        BeanUtils.copyProperties(entity, result);
        //result.setTahunPelajaran(entity.getId().getTahunPelajaran());
        //result.setUrut(entity.getId().getUrut());
        result.setStatus(entity.getStatus());
        return result;
    }

    private SesiAkademikEntity convertReqToEntity(SesiAkademikReq request){
        KurikulumEntity kurikulumEntity = kurikulumRepo.findById(request.getKurikulumId()).orElse(null);
        if(kurikulumEntity == null) {
            Map<String, String> errors = Map.of("kurikulumId","kurikulum tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        TahunAjaranEntity tahunAjaran = tahunAjaranRepo.findById(request.getTahunAjaranId()).orElse(null);
        if(tahunAjaran == null) {
            Map<String, String> errors = Map.of("tahunAjaranId","tahun ajaran tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        SesiAkademikEntity result = new SesiAkademikEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.AKTIF);

        return result;
    }

    private void convertReqToEntity(SesiAkademikReq request, SesiAkademikEntity result){
        result.setKodeKurikulum(request.getKodeKurikulum());
    }
}
