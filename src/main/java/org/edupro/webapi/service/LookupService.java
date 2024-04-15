package org.edupro.webapi.service;

import org.edupro.webapi.model.request.LookupReq;
import org.edupro.webapi.model.response.LookupRes;

import java.util.List;
import java.util.Optional;

public interface LookupService {
    List<LookupRes> get();
    Optional<LookupRes> getById(Integer id);
    Optional<LookupRes> save(LookupReq request);
    Optional<LookupRes> update(LookupReq request, Integer id);
    Optional<LookupRes> delete(Integer id);
}
