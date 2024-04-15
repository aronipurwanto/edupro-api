package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KurikulumMapel;
import org.edupro.webapi.model.entity.KurikulumMapelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KurikulumMapelRepo extends JpaRepository<KurikulumMapel, KurikulumMapelId> {
    List<KurikulumMapel> findAllByStatus(DataStatus status);
    boolean existsById(KurikulumMapelId id);
}