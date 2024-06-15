package org.edupro.webapi.building.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.building.model.BuildingRoomRes;
import org.edupro.webapi.building.service.BuildingRoomService;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.building.model.BuildingRoomReq;
import org.edupro.webapi.base.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ruangan")
@RequiredArgsConstructor
public class BuildingRoomController extends BaseController<BuildingRoomRes> {
    private final BuildingRoomService service;

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
    private ResponseEntity<Response> save(@RequestBody @Valid BuildingRoomReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid BuildingRoomReq request,
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
