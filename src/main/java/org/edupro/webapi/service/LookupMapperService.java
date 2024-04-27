package org.edupro.webapi.service;

import org.edupro.webapi.model.response.LookupRes;

import java.util.List;

public interface LookupMapperService {
    LookupRes getById(String id);
    List<LookupRes> getByGroup(String group);
}
