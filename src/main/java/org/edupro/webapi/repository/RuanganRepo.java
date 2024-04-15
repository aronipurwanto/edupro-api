package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.RuanganEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuanganRepo extends JpaRepository<RuanganEntity, String> {
    @Query("select t from RuanganEntity t join fetch t.gedungEntity")
    List<RuanganEntity> findAllByStatus(DataStatus status);

    @Query("select t from RuanganEntity t join fetch t.gedungEntity where t.kode = :kode")
    Optional<RuanganEntity> findByKode(@Param("kode") String kode);
    boolean existsByKode(String kode);
}
