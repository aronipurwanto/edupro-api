package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelasSiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasSiswaRepo extends JpaRepository<KelasSiswaEntity, String> {
    List<KelasSiswaEntity> findAllByStatus(DataStatus status);
    List<KelasSiswaEntity> findAllByKelasId(String kelasId);
    boolean existsByKelasIdAndSiswaId(String kelasId, String siswaId);
}