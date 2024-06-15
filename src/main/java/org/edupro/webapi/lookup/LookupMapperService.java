package org.edupro.webapi.lookup;

import java.util.List;

public interface LookupMapperService {
    LookupRes getById(String id);
    List<LookupRes> getByGroup(String group);
}
