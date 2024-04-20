package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.CommonLembagaReq;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.model.response.CommonLembagaRes;
import org.edupro.webapi.service.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/level")
@RequiredArgsConstructor
public class LevelController extends BaseController<CommonLembagaRes> {
    private final LevelService service;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.getResponse(result);
    }

    @GetMapping("/{id}/{kode}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid CommonLembagaReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}/{kode}")
    private ResponseEntity<Response> update(@RequestBody @Valid CommonLembagaReq request,
                                            @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}/{kode}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
