package org.edupro.webapi.service;

import org.edupro.webapi.model.entity.KelasId;
import org.edupro.webapi.model.request.KelasReq;
import org.edupro.webapi.model.response.KelasRes;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    List<KelasRes> get();
    Optional<KelasRes> getById(KelasId id);
    Optional<KelasRes> save(KelasReq request);
    Optional<KelasRes> update(KelasReq request);
    Optional<KelasRes> delete(KelasId id);
}
