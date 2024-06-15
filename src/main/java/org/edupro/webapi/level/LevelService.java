package org.edupro.webapi.level;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<LevelRes> get();
    Optional<LevelRes> getById(String id);
    Optional<LevelRes> save(LevelReq request);
    Optional<LevelRes> update(LevelReq request, String id);
    Optional<LevelRes> delete(String id);
}
