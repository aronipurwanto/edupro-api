package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.CommonApiException;
import org.edupro.webapi.model.entity.MapelEntity;
import org.edupro.webapi.model.request.CommonReq;
import org.edupro.webapi.model.response.CommonRes;
import org.edupro.webapi.repository.MapelRepo;
import org.edupro.webapi.service.MapelService;
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
public class MapelServiceImpl implements MapelService {
    private final MapelRepo repo;

    @Override
    public List<CommonRes> get() {
        List<MapelEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CommonRes> getById(String id) {
        MapelEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CommonRes> save(CommonReq request) {
        if(repo.existsByKode(request.getKode())){
            log.info("Save Mapel gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new CommonApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        MapelEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CommonRes> update(CommonReq request, String id) {
        MapelEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<CommonRes> delete(String id) {
        MapelEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);

        return saveOrUpdate(result);
    }

    private Optional<CommonRes> saveOrUpdate(MapelEntity result) {
        try{
            this.repo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Mapel, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Mapel gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new  CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private MapelEntity getEntityById(String id) {
        MapelEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CommonRes convertEntityToRes(MapelEntity entity){
        CommonRes result = new CommonRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private MapelEntity convertReqToEntity(CommonReq request){
        MapelEntity result = new MapelEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(CommonReq request, MapelEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
