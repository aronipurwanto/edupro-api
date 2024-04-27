package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelasIbadahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelasIbadahRepo extends JpaRepository<KelasIbadahEntity, String> {
    List<KelasIbadahEntity> findAllByStatus(DataStatus status);
}
