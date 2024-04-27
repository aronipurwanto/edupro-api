package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.KelasSiswaEntity;
import org.edupro.webapi.model.request.KelasSiswaReq;
import org.edupro.webapi.model.response.KelasSiswaRes;
import org.edupro.webapi.repository.KelasRepo;
import org.edupro.webapi.repository.KelasSiswaRepo;
import org.edupro.webapi.repository.SiswaRepo;
import org.edupro.webapi.service.KelasSiswaService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KelasSiswaServiceImpl implements KelasSiswaService {
    private final KelasSiswaRepo repo;
    private final KelasRepo kelasRepo;
    private final SiswaRepo siswaRepo;

    @Override
    public List<KelasSiswaRes> get() {
        List<KelasSiswaEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<KelasSiswaRes> getById(String id) {
        KelasSiswaEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<KelasSiswaRes> save(KelasSiswaReq request) {
        if(repo.existsByKelasIdAndSiswaId(request.getKelasId(), request.getSiswaId())){
            log.info("Save KelasSiswa gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("siswaId", "kelasId "+ request.getKelasId() +" dan "+ request.getSiswaId()+" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        KelasSiswaEntity result = this.convertReqToEntity(request);
        result.setId(UUID.randomUUID().toString().toUpperCase());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasSiswaRes> save(String kelasId, List<KelasSiswaReq> request) {
        if(!kelasRepo.existsById(kelasId)){
            log.info("Save KelasSiswa gagal, terjadi error : kelasId {} sudah digunakan", kelasId);
            Map<String, String> errors = Map.of("siswaId", MessageFormat.format("kelasId {} tidak ditemukan", kelasId));
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }
        return Optional.empty();
    }

    @Override
    public Optional<KelasSiswaRes> update(KelasSiswaReq request, String id) {
        KelasSiswaEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelasSiswaRes> delete(String id) {
        KelasSiswaEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<KelasSiswaRes> saveOrUpdate(KelasSiswaEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save KelasSiswa, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save KelasSiswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private KelasSiswaEntity getEntityById(String id) {
        KelasSiswaEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private KelasSiswaRes convertEntityToRes(KelasSiswaEntity entity){
        KelasSiswaRes result = new KelasSiswaRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private KelasSiswaEntity convertReqToEntity(KelasSiswaReq request){
        KelasSiswaEntity result = new KelasSiswaEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(KelasSiswaReq request, KelasSiswaEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
