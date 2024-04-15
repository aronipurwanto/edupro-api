package org.edupro.webapi.service;

import org.edupro.webapi.model.request.RuanganReq;
import org.edupro.webapi.model.response.RuanganRes;

import java.util.List;
import java.util.Optional;

public interface RuanganService {
    List<RuanganRes> get();
    Optional<RuanganRes> getById(String kode);
    Optional<RuanganRes> save(RuanganReq request);
    Optional<RuanganRes> update(RuanganReq request, String kode);
    Optional<RuanganRes> delete(String kode);
}
