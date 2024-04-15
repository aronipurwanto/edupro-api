package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CommonLembagaReq;
import org.edupro.webapi.model.response.CommonLembagaRes;

import java.util.List;
import java.util.Optional;

public interface KelompokMapelService {
    List<CommonLembagaRes> get();
    Optional<CommonLembagaRes> getById(Integer id, String kode);
    Optional<CommonLembagaRes> save(CommonLembagaReq request);
    Optional<CommonLembagaRes> update(CommonLembagaReq request, Integer id, String kode);
    Optional<CommonLembagaRes> delete(Integer id, String kode);
}
