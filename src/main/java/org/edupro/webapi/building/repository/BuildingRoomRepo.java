package org.edupro.webapi.building.repository;

import org.edupro.webapi.building.model.BuildingRoomEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRoomRepo extends JpaRepository<BuildingRoomEntity, String> {
    @Query("select t from BuildingRoomEntity t join fetch t.building")
    List<BuildingRoomEntity> findAllByStatus(DataStatus status);

    @Query("select t from BuildingRoomEntity t join fetch t.building where t.code = :kode")
    Optional<BuildingRoomEntity> findByCode(@Param("kode") String kode);
    boolean existsByCode(String kode);
}
