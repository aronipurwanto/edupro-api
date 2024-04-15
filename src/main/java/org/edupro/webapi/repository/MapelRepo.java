package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.MapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapelRepo extends JpaRepository<MapelEntity, String> {
    List<MapelEntity> findAllByStatus(DataStatus status);
    boolean existsByKode(String kode);
}
