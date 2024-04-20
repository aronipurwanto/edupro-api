package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CourseReq;
import org.edupro.webapi.model.response.CourseRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get();
    Optional<CourseRes> getById(String id);
    Optional<CourseRes> save(CourseReq request);
    Optional<CourseRes> update(CourseReq request, String id);
    Optional<CourseRes> delete(String id);
}
