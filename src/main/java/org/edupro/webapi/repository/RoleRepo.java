package org.edupro.webapi.repository;

import org.edupro.webapi.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
    List<RoleEntity> findByNameIn(List<String> roles);
}
