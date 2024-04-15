package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity, String> {
    List<GedungEntity> findAllByStatus(DataStatus status);
    boolean existsByKode(String kode);
}
