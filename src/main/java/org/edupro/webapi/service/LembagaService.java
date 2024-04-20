package org.edupro.webapi.service;

import org.edupro.webapi.model.request.LembagaReq;
import org.edupro.webapi.model.response.LembagaRes;

import java.util.List;
import java.util.Optional;

public interface LembagaService {
    List<LembagaRes> get();
    Optional<LembagaRes> getById(String id);
    Optional<LembagaRes> save(LembagaReq request);
    Optional<LembagaRes> update(LembagaReq request, String id);
    Optional<LembagaRes> delete(String id);
}
