package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelompokMapelEntity;
import org.edupro.webapi.model.entity.KelompokMapelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelompokMapelRepo extends JpaRepository<KelompokMapelEntity, KelompokMapelId> {
    List<KelompokMapelEntity> findAllByStatus(DataStatus status);
    boolean existsById(KelompokMapelId id);
}