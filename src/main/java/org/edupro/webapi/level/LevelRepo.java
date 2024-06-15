package org.edupro.webapi.level;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.institution.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepo extends JpaRepository<LevelEntity, String> {
    List<LevelEntity> findAllByStatus(DataStatus status);
    boolean existsByInstitutionIdAndCode(String institutionId, String code);
    int countAllByInstitutionId(String institutionId);
}