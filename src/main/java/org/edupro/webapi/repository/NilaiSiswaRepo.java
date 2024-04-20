package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.NilaiSiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NilaiSiswaRepo extends JpaRepository<NilaiSiswaEntity, String> {
    List<NilaiSiswaEntity> findAllByStatus(DataStatus status);
}
