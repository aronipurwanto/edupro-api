package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasRepo extends JpaRepository<KelasEntity, String> {
    List<KelasEntity> findAllByStatus(DataStatus status);
    boolean existsByKode(String kode);
}