package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.AcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TahunAjaranRepo extends JpaRepository<AcademicYearEntity, String> {
    List<AcademicYearEntity> findAllByStatus(DataStatus status);
    List<AcademicYearEntity> findAllByKurikulumId(String kurikulumId);
    boolean existsByNama(String nama);
    int countByStatus(DataStatus status);
}