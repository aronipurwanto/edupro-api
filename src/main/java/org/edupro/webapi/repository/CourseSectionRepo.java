package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.CourseEntity;
import org.edupro.webapi.model.entity.CourseSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSectionRepo extends JpaRepository<CourseSectionEntity, String> {
    List<CourseSectionEntity> findAllByCourseId(String courseId);
    List<CourseSectionEntity> findAllByStatus(DataStatus status);
}
