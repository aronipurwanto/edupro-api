package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.LookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupRepo extends JpaRepository<LookupEntity, Integer> {
    List<LookupEntity> findAllByStatus(DataStatus status);
    boolean existsByGroupAndKode(String group, String kode);
}