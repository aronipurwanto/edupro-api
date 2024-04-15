package org.edupro.webapi.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.LevelEntity;
import org.edupro.webapi.model.entity.LevelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepo extends JpaRepository<LevelEntity, String> {
    List<LevelEntity> findAllByStatus(DataStatus status);
    boolean existsById(LevelId id);
}