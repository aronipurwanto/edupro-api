package org.edupro.webapi.service;

import org.edupro.webapi.model.request.KelasReq;
import org.edupro.webapi.model.response.KelasRes;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    List<KelasRes> get();
    Optional<KelasRes> getById(String id);
    Optional<KelasRes> save(KelasReq request);
    Optional<KelasRes> update(KelasReq request, String id);
    Optional<KelasRes> delete(String id);
}
