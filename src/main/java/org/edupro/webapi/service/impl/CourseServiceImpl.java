package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.model.entity.*;
import org.edupro.webapi.model.request.CoursePersonReq;
import org.edupro.webapi.model.request.CourseReq;
import org.edupro.webapi.model.request.CourseSiswaReq;
import org.edupro.webapi.model.response.CoursePersonRes;
import org.edupro.webapi.model.response.CourseRes;
import org.edupro.webapi.model.response.CourseSectionRes;
import org.edupro.webapi.model.response.CourseSiswaRes;
import org.edupro.webapi.repository.*;
import org.edupro.webapi.service.BaseService;
import org.edupro.webapi.service.CourseService;
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
public class CourseServiceImpl extends BaseService implements CourseService {
    private final MapelRepo mapelRepo;
    private final CourseRepo repo;
    private final CoursePersonRepo coursePersonRepo;
    private final CourseSiswaRepo courseSiswaRepo;
    private final SiswaRepo siswaRepo;
    private final PersonRepo personRepo;

    @Override
    public List<CourseRes> get() {
        List<CourseEntity> result = this.repo.findAllByStatus(DataStatus.AKTIF);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public List<CourseRes> getByUserId(String userId) {
        List<CourseEntity> result = this.repo.findAllByStatusAndCreatedBy(DataStatus.AKTIF, userId);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseSiswaRes> saveSiswa(String id, CourseSiswaReq request) {
        CourseEntity course  = this.getEntityById(request.getCourseId());
        SiswaEntity siswa = this.siswaRepo.findById(request.getSiswaId()).orElse(null);
        if(siswa == null){
            return Optional.empty();
        }

        CourseSiswaEntity entity = new CourseSiswaEntity(course, siswa);
        try{
            courseSiswaRepo.saveAndFlush(entity);
            CourseSiswaRes result = new CourseSiswaRes();
            BeanUtils.copyProperties(entity, result);
            return Optional.of(result);
        }catch (DataIntegrityViolationException e){
            log.error("Save Course Siswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public List<CourseSiswaRes> saveSiswaList(List<CourseSiswaReq> request) {
        return List.of();
    }

    @Override
    public Optional<CoursePersonRes> savePerson(String id, CoursePersonReq request) {
        CourseEntity course  = this.getEntityById(request.getCourseId());
        PersonEntity person = this.personRepo.findById(request.getPersonId()).orElse(null);
        if(person == null){
            return Optional.empty();
        }

        CoursePersonEntity entity = new CoursePersonEntity(course, person);
        try{
            coursePersonRepo.saveAndFlush(entity);
            CoursePersonRes result = new CoursePersonRes();
            BeanUtils.copyProperties(entity, result);
            return Optional.of(result);
        }catch (DataIntegrityViolationException e){
            log.error("Save Course Person gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public List<CoursePersonRes> savePersonList(List<CoursePersonReq> request) {
        return List.of();
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        CourseEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToResWithSection(result));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        CourseEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);

        if(!this.getUserInfo().getUserId().isEmpty()){
            result.setCreatedBy(this.getUserInfo().getUserId());
        }
        return saveOrUpdate(result);
    }

    private Optional<CourseRes> saveOrUpdate(CourseEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Course, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Course gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private CourseEntity getEntityById(String id) {
        CourseEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CourseRes convertEntityToRes(CourseEntity entity){
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private CourseRes convertEntityToResWithSection(CourseEntity entity){
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);

        if(!entity.getCourseSectionList().isEmpty()){
            List<CourseSectionRes> sectionResList = entity.getCourseSectionList().stream().map(this::convertEntityToSectionRes).toList();
            result.setSections(sectionResList);
        }
        return result;
    }

    private CourseSectionRes convertEntityToSectionRes(CourseSectionEntity entity){
        CourseSectionRes result = new CourseSectionRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private CourseEntity convertReqToEntity(CourseReq request){
        CourseEntity result = new CourseEntity();
        BeanUtils.copyProperties(request, result);

        MapelEntity mapel = null;
        if(request.getMapelId() != null && !request.getMapelId().isEmpty()) {
            mapel = mapelRepo.findById(request.getMapelId()).orElse(null);
        }
        
        if(mapel != null) {
            result.setMapelId(mapel.getId());
            result.setKodeMapel(mapel.getKode());
        }

        String userId = this.getUserInfo().getUserId();
        if(userId != null && !userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
        return result;
    }

    private void convertReqToEntity(CourseReq request, CourseEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        if(!this.getUserInfo().getUserId().isEmpty()){
            result.setCreatedBy(this.getUserInfo().getUserId());
            result.setUpdatedBy(this.getUserInfo().getUserId());
        }
    }
}
