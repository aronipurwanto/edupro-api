package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.SesiAkademikEntity;
import org.edupro.webapi.model.entity.SesiAkademikId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesiAkademikRepo extends JpaRepository<SesiAkademikEntity, SesiAkademikId> {
    List<SesiAkademikEntity> findAllByStatus(DataStatus status);
    boolean existsById(SesiAkademikId id);
}