package org.edupro.webapi.service;

import org.edupro.webapi.model.request.CommonReq;
import org.edupro.webapi.model.request.KurikulumReq;
import org.edupro.webapi.model.response.CommonRes;
import org.edupro.webapi.model.response.KurikulumRes;

import java.util.List;
import java.util.Optional;

public interface KurikulumService {
    List<KurikulumRes> get();
    Optional<KurikulumRes> getById(String kode);
    Optional<KurikulumRes> save(KurikulumReq request);
    Optional<KurikulumRes> update(KurikulumReq request, String kode);
    Optional<KurikulumRes> delete(String kode);
}
