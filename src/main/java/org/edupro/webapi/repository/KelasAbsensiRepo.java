package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.ClassAbsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasAbsensiRepo extends JpaRepository<ClassAbsentEntity, String> {
    List<ClassAbsentEntity> findAllByStatus(DataStatus status);
}
