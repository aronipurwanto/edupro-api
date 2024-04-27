package org.edupro.webapi.service;

import org.edupro.webapi.model.request.PersonReq;
import org.edupro.webapi.model.response.PersonRes;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonRes> get();
    Optional<PersonRes> getById(String id);
    Optional<PersonRes> save(PersonReq request);
    Optional<PersonRes> update(PersonReq request, String id);
    Optional<PersonRes> delete(String id);
}
