package org.edupro.webapi.service;

import org.edupro.webapi.model.request.SiswaReq;
import org.edupro.webapi.model.response.SiswaRes;

import java.util.List;
import java.util.Optional;

public interface SiswaService {
    List<SiswaRes> get();
    Optional<SiswaRes> getById(String id);
    Optional<SiswaRes> save(SiswaReq request);
    Optional<SiswaRes> update(SiswaReq request, String id);
    Optional<SiswaRes> delete(String id);
}
