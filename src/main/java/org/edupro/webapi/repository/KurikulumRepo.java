package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.CurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KurikulumRepo extends JpaRepository<CurriculumEntity, String> {
    List<CurriculumEntity> findAllByStatus(DataStatus status);
    Optional<CurriculumEntity> findByKode(String kode);
    boolean existsByKode(String kode);
    int countAllByStatus(DataStatus status);
}
