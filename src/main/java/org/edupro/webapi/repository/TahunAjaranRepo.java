package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.TahunAjaranEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TahunAjaranRepo extends JpaRepository<TahunAjaranEntity, String> {
    List<TahunAjaranEntity> findAllByStatus(DataStatus status);
    List<TahunAjaranEntity> findAllByKurikulumId(String kurikulumId);
    boolean existsByNama(String nama);
    int countByStatus(DataStatus status);
}