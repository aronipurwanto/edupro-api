package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.KelompokMapelEntity;
import org.edupro.webapi.model.entity.KelompokMapelId;
import org.edupro.webapi.model.request.CommonLembagaReq;
import org.edupro.webapi.model.response.CommonLembagaRes;
import org.edupro.webapi.repository.KelompokMapelRepo;
import org.edupro.webapi.service.KelompokMapelService;
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
public class KelompokMapelServiceImpl implements KelompokMapelService {
    private final KelompokMapelRepo repo;

    @Override
    public List<CommonLembagaRes> get() {
        List<KelompokMapelEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CommonLembagaRes> getById(Integer id, String kode) {
        KelompokMapelEntity result = this.getEntityById(new KelompokMapelId(id, kode));

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CommonLembagaRes> save(CommonLembagaReq request) {
        var id = new KelompokMapelId(request.getIdLembaga(),request.getKode());
        if(repo.existsById(id)){
            Map<String, String> errors = Map.of("kode", "Id Lembaga "+ request.getIdLembaga()+" dan Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        KelompokMapelEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CommonLembagaRes> update(CommonLembagaReq request, Integer id, String kode) {
        KelompokMapelEntity result = this.getEntityById(new KelompokMapelId(id, kode));

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CommonLembagaRes> delete(Integer id, String kode) {
        KelompokMapelEntity result = this.getEntityById(new KelompokMapelId(id, kode));
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    private Optional<CommonLembagaRes> saveOrUpdate(KelompokMapelEntity result) {
        try{
            this.repo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelompok Mapel, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Kelompok Mapel gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private KelompokMapelEntity getEntityById(KelompokMapelId KelompokMapelId) {
        KelompokMapelEntity result = this.repo.findById(KelompokMapelId).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Id Lembaga "+ KelompokMapelId.getIdLembaga()+" dan Kode "+ KelompokMapelId.getKode() +" tidak ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CommonLembagaRes convertEntityToRes(KelompokMapelEntity entity){
        CommonLembagaRes result = new CommonLembagaRes();
        BeanUtils.copyProperties(entity, result);
        result.setIdLembaga(entity.getId().getIdLembaga());
        result.setKode(entity.getId().getKode());
        return result;
    }

    private KelompokMapelEntity convertReqToEntity(CommonLembagaReq request){
        KelompokMapelId id = new KelompokMapelId(request.getIdLembaga(), request.getKode());
        return new KelompokMapelEntity(id,request.getNama(), DataStatus.AKTIF);
    }

    private void convertReqToEntity(CommonLembagaReq request, KelompokMapelEntity result){
        result.setNama(request.getNama());
    }
}
