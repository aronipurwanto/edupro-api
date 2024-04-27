package org.edupro.webapi.controller.v2;

import lombok.RequiredArgsConstructor;
import org.edupro.webapi.controller.v1.BaseController;
import org.edupro.webapi.model.response.LookupRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.LookupMapperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/lookup")
@RequiredArgsConstructor
public class LookupMapperController extends BaseController<LookupRes> {
    private final LookupMapperService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response> lookupGetById(@PathVariable("id") String id) {
        return getResponse(service.getById(id));
    }

    @GetMapping("/group/{group}")
    public ResponseEntity<Response> lookupByGroup(@PathVariable("group") String group) {
        return getResponse(service.getByGroup(group));
    }
}
