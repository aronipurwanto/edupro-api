package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CommonLembagaReq;
import org.edupro.webapi.model.response.CommonLembagaRes;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<CommonLembagaRes> get();
    Optional<CommonLembagaRes> getById(String id);
    Optional<CommonLembagaRes> save(CommonLembagaReq request);
    Optional<CommonLembagaRes> update(CommonLembagaReq request, String id);
    Optional<CommonLembagaRes> delete(String id);
}
