package org.edupro.webapi.service;

import org.edupro.webapi.model.request.SesiAkademikReq;
import org.edupro.webapi.model.response.SesiAkademikRes;

import java.util.List;
import java.util.Optional;

public interface SesiAkademikService {
    List<SesiAkademikRes> get();
    Optional<SesiAkademikRes> getById(String id);
    Optional<SesiAkademikRes> save(SesiAkademikReq request);
    Optional<SesiAkademikRes> update(SesiAkademikReq request, String id);
    Optional<SesiAkademikRes> delete(String id);
}
