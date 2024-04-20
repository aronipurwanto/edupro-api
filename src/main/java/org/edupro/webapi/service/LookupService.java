package org.edupro.webapi.service;

import org.edupro.webapi.model.request.LookupReq;
import org.edupro.webapi.model.response.LookupRes;

import java.util.List;
import java.util.Optional;

public interface LookupService {
    List<LookupRes> get();
    Optional<LookupRes> getById(String id);
    List<LookupRes> getByGroup(String group);
    Optional<LookupRes> save(LookupReq request);
    Optional<LookupRes> update(LookupReq request, String id);
    Optional<LookupRes> delete(String id);
}
