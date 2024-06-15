package org.edupro.webapi.building.service;

import org.edupro.webapi.building.model.BuildingRoomRes;
import org.edupro.webapi.building.model.BuildingRoomReq;

import java.util.List;
import java.util.Optional;

public interface BuildingRoomService {
    List<BuildingRoomRes> get();
    Optional<BuildingRoomRes> getById(String Id);
    Optional<BuildingRoomRes> save(BuildingRoomReq request);
    Optional<BuildingRoomRes> update(BuildingRoomReq request, String id);
    Optional<BuildingRoomRes> delete(String id);
}
