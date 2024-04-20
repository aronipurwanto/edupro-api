package org.edupro.webapi.service;

import org.edupro.webapi.model.request.KelasMapelReq;
import org.edupro.webapi.model.response.KelasMapelRes;

import java.util.List;
import java.util.Optional;

public interface KelasMapelService {
    List<KelasMapelRes> get();
    Optional<KelasMapelRes> getById(String id);
    Optional<KelasMapelRes> save(KelasMapelReq request);
    Optional<KelasMapelRes> update(KelasMapelReq request, String id);
    Optional<KelasMapelRes> delete(String id);
}
