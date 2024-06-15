package org.edupro.webapi.lookup;

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
