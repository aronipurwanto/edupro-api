package org.edupro.webapi.service;

import org.edupro.webapi.model.request.NilaiSiswaReq;
import org.edupro.webapi.model.response.NilaiSiswaRes;

import java.util.List;
import java.util.Optional;

public interface NilaiSiswaService {
    List<NilaiSiswaRes> get();
    Optional<NilaiSiswaRes> getById(String id);
    Optional<NilaiSiswaRes> save(NilaiSiswaReq request);
    Optional<NilaiSiswaRes> update(NilaiSiswaReq request, String id);
    Optional<NilaiSiswaRes> delete(String id);
}
