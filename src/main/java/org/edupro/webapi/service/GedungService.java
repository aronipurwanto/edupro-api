package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CommonReq;
import org.edupro.webapi.model.response.CommonRes;

import java.util.List;
import java.util.Optional;

public interface GedungService {
    List<CommonRes> get();
    Optional<CommonRes> getById(String kode);
    Optional<CommonRes> save(CommonReq request);
    Optional<CommonRes> update(CommonReq request, String kode);
    Optional<CommonRes> delete(String kode);
}
