package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.PersonReq;
import org.edupro.webapi.model.response.PersonRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController extends BaseController<PersonRes> {
    private final PersonService service;

    @GetMapping("")
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.getResponse(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping("")
    private ResponseEntity<Response> save(@RequestBody @Valid PersonReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}/{kode}")
    private ResponseEntity<Response> update(@RequestBody @Valid PersonReq request,
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
