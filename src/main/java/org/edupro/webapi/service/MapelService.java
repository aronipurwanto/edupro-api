package org.edupro.webapi.service;

import org.edupro.webapi.model.request.MapelReq;
import org.edupro.webapi.model.response.MapelRes;

import java.util.List;
import java.util.Optional;

public interface MapelService {
    List<MapelRes> get();
    Optional<MapelRes> getById(String kode);
    Optional<MapelRes> save(MapelReq request);
    Optional<MapelRes> update(MapelReq request, String kode);
    Optional<MapelRes> delete(String kode);
}
