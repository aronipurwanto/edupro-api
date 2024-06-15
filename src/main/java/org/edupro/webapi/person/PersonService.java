package org.edupro.webapi.person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonRes> get();
    Optional<PersonRes> getById(String id);
    Optional<PersonRes> save(PersonReq request);
    Optional<PersonRes> update(PersonReq request, String id);
    Optional<PersonRes> delete(String id);
}
