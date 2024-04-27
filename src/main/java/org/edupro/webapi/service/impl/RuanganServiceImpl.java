package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.GedungEntity;
import org.edupro.webapi.model.entity.RuanganEntity;
import org.edupro.webapi.model.request.RuanganReq;
import org.edupro.webapi.model.response.RuanganRes;
import org.edupro.webapi.repository.GedungRepo;
import org.edupro.webapi.repository.RuanganRepo;
import org.edupro.webapi.service.RuanganService;
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
public class RuanganServiceImpl implements RuanganService {
    private final RuanganRepo repo;
    private final GedungRepo gedungRepo;

    @Override
    public List<RuanganRes> get() {
        List<RuanganEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<RuanganRes> getById(String id) {
        RuanganEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<RuanganRes> save(RuanganReq request) {
        if(repo.existsByKode(request.getKode())){
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        RuanganEntity result = this.convertReqToEntity(request);
        result.setId(UUID.randomUUID().toString().toUpperCase());

        return saveOrUpdate(result);
    }

    @Override
    public Optional<RuanganRes> update(RuanganReq request, String id) {
        RuanganEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<RuanganRes> delete(String id) {
        RuanganEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    private Optional<RuanganRes> saveOrUpdate(RuanganEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Ruangan, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Ruangan gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private GedungEntity getGedungById(String id) {
        GedungEntity result = this.gedungRepo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private RuanganEntity getEntityById(String id) {
        RuanganEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private RuanganRes convertEntityToRes(RuanganEntity entity){
        RuanganRes result = new RuanganRes();
        BeanUtils.copyProperties(entity, result);
        if(entity.getGedungEntity() != null){
            if(entity.getGedungEntity().getKode() != null) result.setKodeGedung(entity.getGedungEntity().getKode());

            if (entity.getGedungEntity().getNama() != null) result.setNamaGedung(entity.getGedungEntity().getNama());
        }
        return result;
    }

    private RuanganEntity convertReqToEntity(RuanganReq request){
        GedungEntity gedung =  this.getGedungById(request.getGedungId());
        if(gedung.getId().isEmpty()){
            Map<String, String> errors = Map.of("gedungId", "gedungId "+ request.getGedungId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        RuanganEntity result = new RuanganEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());

        result.setGedungId(gedung.getId());
        return result;
    }

    private void convertReqToEntity(RuanganReq request, RuanganEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        GedungEntity gedungEntity =  this.getGedungById(request.getGedungId());
        result.setGedungId(gedungEntity.getId());
    }
}
