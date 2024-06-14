package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.StudentPrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasIbadahRepo extends JpaRepository<StudentPrayEntity, String> {
    List<StudentPrayEntity> findAllByStatus(DataStatus status);
}
