package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.SiswaReq;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.model.response.SiswaRes;
import org.edupro.webapi.model.response.SiswaRes;
import org.edupro.webapi.service.SiswaService;
import org.edupro.webapi.service.SiswaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/siswa")
@RequiredArgsConstructor
public class SiswaController extends BaseController<SiswaRes> {
    private final SiswaService service;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = service.get();
        return this.get(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);

        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid SiswaReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid SiswaReq request,
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
