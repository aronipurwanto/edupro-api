package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KurikulumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KurikulumRepo extends JpaRepository<KurikulumEntity, String> {
    List<KurikulumEntity> findAllByStatus(DataStatus status);
    boolean existsByKode(String kode);
}
