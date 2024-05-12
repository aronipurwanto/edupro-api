package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CourseSectionReq;
import org.edupro.webapi.model.response.CourseSectionRes;

import java.util.List;
import java.util.Optional;

public interface CourseSectionService {
    List<CourseSectionRes> getByCourseId(String courseId);
    Optional<CourseSectionRes> getById(String id);
    Optional<CourseSectionRes> save(CourseSectionReq request);
    Optional<CourseSectionRes> update(CourseSectionReq request, String id);
    Optional<CourseSectionRes> delete(String id);
}
