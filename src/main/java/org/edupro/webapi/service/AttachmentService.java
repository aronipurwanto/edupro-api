package org.edupro.webapi.service;

import org.edupro.webapi.model.request.AttachmentReq;
import org.edupro.webapi.model.response.AttachmentRes;

import java.util.List;
import java.util.Optional;

public interface AttachmentService {
    List<AttachmentRes> get();
    Optional<AttachmentRes> getById(String id);
    Optional<AttachmentRes> save(AttachmentReq request);
    Optional<AttachmentRes> update(AttachmentReq request, String id);
    Optional<AttachmentRes> delete(String id);
}
