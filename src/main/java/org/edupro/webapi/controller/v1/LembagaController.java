package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.constant.CommonConstant;
import org.edupro.webapi.model.entity.BaseEntity;
import org.edupro.webapi.model.request.LembagaReq;
import org.edupro.webapi.model.response.LembagaRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.LembagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lembaga")
@RequiredArgsConstructor
public class LembagaController extends BaseController<LembagaRes> {
    private final LembagaService service;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.get(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") Integer id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid LembagaReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid LembagaReq request,
                                            @PathVariable("id") Integer id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") Integer id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
