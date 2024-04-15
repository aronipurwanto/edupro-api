package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.LookupGroup;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.model.request.LookupReq;
import org.edupro.webapi.model.response.CommonRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.model.response.LookupRes;
import org.edupro.webapi.service.LookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lookup")
@RequiredArgsConstructor
public class LookupController extends BaseController<LookupRes> {
    private final LookupService service;

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

    @GetMapping("/group")
    private ResponseEntity<Response> getGroup(){
        return ResponseEntity.ok()
                .body(Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(MessageApp.SUCCESS)
                        .data(this.getLookupGroup())
                        .build()
                );
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid LookupReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid LookupReq request,
                                            @PathVariable("id") Integer id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") Integer id){
        var result = service.delete(id);
        return getResponse(result);
    }

    public List<CommonRes> getLookupGroup(){
        List<CommonRes> result = new ArrayList<>();
        for(Map.Entry entry: LookupGroup.getAllValue().entrySet()){
            result.add(new CommonRes(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()), DataStatus.AKTIF));
        }

        return result;
    }
}
