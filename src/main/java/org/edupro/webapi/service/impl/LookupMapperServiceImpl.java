package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.webapi.mapper.LookupMapper;
import org.edupro.webapi.model.response.LookupRes;
import org.edupro.webapi.service.LookupMapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookupMapperServiceImpl implements LookupMapperService {
    private final LookupMapper lookupMapper;

    @Override
    public LookupRes getById(String id) {
        return lookupMapper.getById(id);
    }

    @Override
    public List<LookupRes> getByGroup(String group) {
        return lookupMapper.getByGroup(group);
    }
}
