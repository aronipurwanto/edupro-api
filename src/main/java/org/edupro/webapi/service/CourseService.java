package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CoursePersonReq;
import org.edupro.webapi.model.request.CourseReq;
import org.edupro.webapi.model.request.CourseSiswaReq;
import org.edupro.webapi.model.response.CoursePeopleRes;
import org.edupro.webapi.model.response.CoursePersonRes;
import org.edupro.webapi.model.response.CourseRes;
import org.edupro.webapi.model.response.CourseSiswaRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get();
    List<CourseRes> getByUserId(String userId);
    Optional<CoursePeopleRes> getPeopleById(String id);
    Optional<CourseSiswaRes> saveSiswa(String id, CourseSiswaReq request);
    List<CourseSiswaRes> saveSiswaList(List<CourseSiswaReq> request);
    Optional<CoursePersonRes> savePerson(String id, CoursePersonReq request);
    List<CoursePersonRes> savePersonList(List<CoursePersonReq> request);
    Optional<CourseRes> getById(String id);
    Optional<CourseRes> save(CourseReq request);
    Optional<CourseRes> update(CourseReq request, String id);
    Optional<CourseRes> delete(String id);
}
