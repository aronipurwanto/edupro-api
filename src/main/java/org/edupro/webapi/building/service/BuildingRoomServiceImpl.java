package org.edupro.webapi.building.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.building.model.BuildingEntity;
import org.edupro.webapi.building.repository.BuildingRepo;
import org.edupro.webapi.building.repository.BuildingRoomRepo;
import org.edupro.webapi.building.model.BuildingRoomEntity;
import org.edupro.webapi.building.model.BuildingRoomRes;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.building.model.BuildingRoomReq;
import org.edupro.webapi.BaseService;
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
public class BuildingRoomServiceImpl extends BaseService implements BuildingRoomService {
    private final BuildingRoomRepo repo;
    private final BuildingRepo buildingRepo;

    @Override
    public List<BuildingRoomRes> get() {
        List<BuildingRoomEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<BuildingRoomRes> getById(String id) {
        BuildingRoomEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<BuildingRoomRes> save(BuildingRoomReq request) {
        if(repo.existsByCode(request.getKode())){
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getKode() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        BuildingRoomEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());

        return saveOrUpdate(result);
    }

    @Override
    public Optional<BuildingRoomRes> update(BuildingRoomReq request, String id) {
        BuildingRoomEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<BuildingRoomRes> delete(String id) {
        BuildingRoomEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    private Optional<BuildingRoomRes> saveOrUpdate(BuildingRoomEntity result) {
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

    private BuildingEntity getGedungById(String id) {
        BuildingEntity result = this.buildingRepo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private BuildingRoomEntity getEntityById(String id) {
        BuildingRoomEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private BuildingRoomRes convertEntityToRes(BuildingRoomEntity entity){
        BuildingRoomRes result = new BuildingRoomRes();
        BeanUtils.copyProperties(entity, result);
        if(entity.getBuilding() != null){
            if(entity.getBuilding().getCode() != null) result.setKodeGedung(entity.getBuilding().getCode());

            if (entity.getBuilding().getName() != null) result.setNamaGedung(entity.getBuilding().getName());
        }
        return result;
    }

    private BuildingRoomEntity convertReqToEntity(BuildingRoomReq request){
        BuildingEntity gedung =  this.getGedungById(request.getGedungId());
        if(gedung.getId().isEmpty()){
            Map<String, String> errors = Map.of("gedungId", "gedungId "+ request.getGedungId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        BuildingRoomEntity result = new BuildingRoomEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(BuildingRoomReq request, BuildingRoomEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        BuildingEntity buildingEntity =  this.getGedungById(request.getGedungId());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
