package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CommonReq;
import org.edupro.webapi.model.response.CommonRes;
import org.edupro.webapi.model.response.GedungRes;

import java.util.List;
import java.util.Optional;

public interface GedungService {
    List<GedungRes> get();
    Optional<GedungRes> getById(String kode);
    Optional<GedungRes> save(CommonReq request);
    Optional<GedungRes> update(CommonReq request, String kode);
    Optional<GedungRes> delete(String kode);
}
