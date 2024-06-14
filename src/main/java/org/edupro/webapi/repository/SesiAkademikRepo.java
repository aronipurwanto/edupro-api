package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.AcademicSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesiAkademikRepo extends JpaRepository<AcademicSessionEntity, String> {
    List<AcademicSessionEntity> findAllByStatus(DataStatus status);
    boolean existsByKurikulumIdAndTahunAjaranIdAndSemester(String kurikulumId, String tahunAjaranId, Integer semester);
}