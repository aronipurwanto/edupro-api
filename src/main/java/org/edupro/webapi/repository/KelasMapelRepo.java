package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelasMapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasMapelRepo extends JpaRepository<KelasMapelEntity, String> {
    List<KelasMapelEntity> findAllByStatus(DataStatus status);
    boolean existsByKelasIdAndMapelId(String kelasId, String mapelId);
}