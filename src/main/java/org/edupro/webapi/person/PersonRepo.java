package org.edupro.webapi.person;

import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<PersonEntity, String> {
    List<PersonEntity> findAllByStatus(DataStatus status);
    boolean existsByNik(String nik);
    int countByStatus(DataStatus status);
}