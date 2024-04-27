package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelasAbsensiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasAbsensiRepo extends JpaRepository<KelasAbsensiEntity, String> {
    List<KelasAbsensiEntity> findAllByStatus(DataStatus status);
}
