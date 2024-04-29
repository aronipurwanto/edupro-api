package org.edupro.webapi.service;

import org.edupro.webapi.model.request.LevelReq;
import org.edupro.webapi.model.response.LevelRes;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<LevelRes> get();
    Optional<LevelRes> getById(String id);
    Optional<LevelRes> save(LevelReq request);
    Optional<LevelRes> update(LevelReq request, String id);
    Optional<LevelRes> delete(String id);
}
