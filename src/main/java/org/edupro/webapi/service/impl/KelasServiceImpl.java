package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.KelasEntity;
import org.edupro.webapi.model.request.KelasReq;
import org.edupro.webapi.model.response.KelasRes;
import org.edupro.webapi.repository.*;
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
    private final RuanganRepo ruanganRepo;
    private final LembagaRepo lembagaRepo;
    private final SesiAkademikRepo sesiAkademikRepo;
    private final PersonRepo personRepo;

    @Override
    public List<KelasRes> get() {
        List<KelasEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<KelasRes> getById(String id) {
        KelasEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<KelasRes> save(KelasReq request) {
        if(repo.existsByKode(request.getKode())){
            log.info("Save Kelas gagal, terjadi error : id sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        KelasEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasRes> update(KelasReq request, String id) {
        KelasEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasRes> delete(String id) {
        KelasEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<KelasRes> saveOrUpdate(KelasEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelas, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Kelas gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private KelasEntity getEntityById(String id) {
        KelasEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private KelasRes convertEntityToRes(KelasEntity entity){
        KelasRes result = new KelasRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private KelasEntity convertReqToEntity(KelasReq request){
        KelasEntity result = new KelasEntity();
        if(!ruanganRepo.existsByKode(request.getRuangId())){
            Map<String, String> errors = Map.of("ruangId", "ruangId "+ request.getRuangId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        if(!lembagaRepo.existsById(request.getLembagaId())){
            Map<String, String> errors = Map.of("lembagaId", "lembagaId "+ request.getLembagaId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        if(!sesiAkademikRepo.existsById(request.getSesiAkademikId())){
            Map<String, String> errors = Map.of("sesiAkademikId", "sesiAkademikId "+ request.getLembagaId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        if(!personRepo.existsById(request.getWaliKelasId())){
            Map<String, String> errors = Map.of("waliKelasId", "waliKelasId "+ request.getWaliKelasId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

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
