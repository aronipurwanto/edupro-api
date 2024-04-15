package org.edupro.webapi.repository;

import org.edupro.webapi.model.entity.LembagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LembagaRepo extends JpaRepository<LembagaEntity, Integer> {
}
