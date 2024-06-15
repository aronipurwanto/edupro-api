package org.edupro.webapi.building.service;

import org.edupro.webapi.building.model.BuildingRes;
import org.edupro.webapi.courses.model.CommonReq;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    List<BuildingRes> get();
    Optional<BuildingRes> getById(String kode);
    Optional<BuildingRes> save(CommonReq request);
    Optional<BuildingRes> update(CommonReq request, String kode);
    Optional<BuildingRes> delete(String kode);
}
