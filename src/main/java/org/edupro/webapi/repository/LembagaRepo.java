package org.edupro.webapi.repository;

import org.edupro.webapi.model.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LembagaRepo extends JpaRepository<InstitutionEntity, String> {
    boolean existsByNama(String nama);
    boolean existsByNamaSingkat(String namaSingkat);
    Optional<InstitutionEntity> findByNama(String nama);
}
