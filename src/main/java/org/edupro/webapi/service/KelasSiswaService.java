package org.edupro.webapi.service;

import org.edupro.webapi.model.request.KelasSiswaReq;
import org.edupro.webapi.model.response.KelasSiswaRes;

import java.util.List;
import java.util.Optional;

public interface KelasSiswaService {
    List<KelasSiswaRes> get();
    Optional<KelasSiswaRes> getById(String kode);
    Optional<KelasSiswaRes> save(KelasSiswaReq request);
    Optional<KelasSiswaRes> save(String kelasId, List<KelasSiswaReq> request);
    Optional<KelasSiswaRes> update(KelasSiswaReq request, String kode);
    Optional<KelasSiswaRes> delete(String kode);
}
