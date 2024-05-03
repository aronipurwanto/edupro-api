package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.KurikulumReq;
import org.edupro.webapi.model.response.CommonRes;
import org.edupro.webapi.model.response.KurikulumRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.KurikulumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kurikulum")
@RequiredArgsConstructor
public class KurikulumController extends BaseController<KurikulumRes>{
    private final KurikulumService service;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.getResponse(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid KurikulumReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid KurikulumReq request,
                                            @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
