package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.StudentScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NilaiSiswaRepo extends JpaRepository<StudentScoreEntity, String> {
    List<StudentScoreEntity> findAllByStatus(DataStatus status);
}
