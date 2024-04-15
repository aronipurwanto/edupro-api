package org.edupro.webapi.service;

import org.edupro.webapi.model.request.TahunAjaranReq;
import org.edupro.webapi.model.response.TahunAjaranRes;

import java.util.List;
import java.util.Optional;

public interface TahunAjaranService {
    List<TahunAjaranRes> get();
    Optional<TahunAjaranRes> getById(String id);
    Optional<TahunAjaranRes> save(TahunAjaranReq request);
    Optional<TahunAjaranRes> update(TahunAjaranReq request, String id);
    Optional<TahunAjaranRes> delete(String id);
}
