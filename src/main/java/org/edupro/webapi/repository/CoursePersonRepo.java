package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.CoursePersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePersonRepo extends JpaRepository<CoursePersonEntity, String> {
    List<CoursePersonEntity> findAllByCourseIdAndStatus(String courseId, DataStatus status);
    Integer countAllByStatus(DataStatus status);
}
