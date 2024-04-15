package org.edupro.webapi.service;

import org.edupro.webapi.model.request.SesiAkademikReq;
import org.edupro.webapi.model.response.SesiAkademikRes;

import java.util.List;
import java.util.Optional;

public interface SesiService {
    List<SesiAkademikRes> get();
    Optional<SesiAkademikRes> getById(Integer id, Integer kode);
    Optional<SesiAkademikRes> save(SesiAkademikReq request);
    Optional<SesiAkademikRes> update(SesiAkademikReq request, Integer id, Integer kode);
    Optional<SesiAkademikRes> delete(Integer id, Integer kode);
}
