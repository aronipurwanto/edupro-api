package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.SesiAkademikReq;
import org.edupro.webapi.model.response.SesiAkademikRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.SesiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sesi")
@RequiredArgsConstructor
public class SesiController extends BaseController<SesiAkademikRes> {
    private final SesiService service;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.get(result);
    }

    @GetMapping("/{id}/{urut}")
    private ResponseEntity<Response> get(@PathVariable("id") Integer id,
                                         @PathVariable("urut") Integer urut){
        var result = service.getById(id, urut);
        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid SesiAkademikReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}/{urut}")
    private ResponseEntity<Response> update(@RequestBody @Valid SesiAkademikReq request,
                                            @PathVariable("id") Integer id,
                                            @PathVariable("urut") Integer urut){
        var result = service.update(request, id, urut);
        return getResponse(result);
    }

    @DeleteMapping("/{id}/{urut}")
    private ResponseEntity<Response> delete(@PathVariable("id") Integer id,
                                            @PathVariable("urut") Integer urut){
        var result = service.delete(id, urut);
        return getResponse(result);
    }
}
