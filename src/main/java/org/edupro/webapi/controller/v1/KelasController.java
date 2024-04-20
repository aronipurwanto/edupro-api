package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.KelasReq;
import org.edupro.webapi.model.response.KelasRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.KelasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kelompok")
@RequiredArgsConstructor
public class KelasController extends BaseController<KelasRes> {
    private final KelasService service;

    @GetMapping("/mapel")
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.getResponse(result);
    }

    @GetMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping("/mapel")
    private ResponseEntity<Response> save(@RequestBody @Valid KelasReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> update(@RequestBody @Valid KelasReq request,
                                            @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
