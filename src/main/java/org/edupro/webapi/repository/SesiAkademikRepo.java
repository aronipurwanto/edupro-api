package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.SesiAkademikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesiAkademikRepo extends JpaRepository<SesiAkademikEntity, String> {
    List<SesiAkademikEntity> findAllByStatus(DataStatus status);
    boolean existsByKurikulumIdAndTahunAjaranIdAndUrut(String kurikulumId, String tahunAjaranId, Integer urut);
}