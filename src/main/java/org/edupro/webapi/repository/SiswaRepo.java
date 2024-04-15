package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.SiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiswaRepo extends JpaRepository<SiswaEntity, String> {
    Optional<SiswaEntity> findByNisn(String name);
    List<SiswaEntity> findAllByStatus(DataStatus status);
    boolean existsByNisn(String nisn);
}
