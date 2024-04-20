package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.CourseResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseResourceRepo extends JpaRepository<CourseResourceEntity, String> {
    List<CourseResourceEntity> findAllByCourseSectionId(String courseSectionId);
    List<CourseResourceEntity> findAllByStatus(DataStatus status);
}
