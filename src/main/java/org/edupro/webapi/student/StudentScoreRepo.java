package org.edupro.webapi.student;

import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentScoreRepo extends JpaRepository<StudentScoreEntity, String> {
    List<StudentScoreEntity> findAllByStatus(DataStatus status);
}
