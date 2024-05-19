package org.edupro.webapi.controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.model.request.CoursePersonReq;
import org.edupro.webapi.model.request.CourseReq;
import org.edupro.webapi.model.request.CourseSiswaReq;
import org.edupro.webapi.model.response.CourseRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController extends BaseController<CourseRes>{
    private final CourseService service;

    @GetMapping
    private ResponseEntity<Response> get(HttpServletRequest request){
        var result = service.get();

        log.info("header: {}", request.getHeader("Authorization"));
        return this.getResponse(result);
    }

    @GetMapping("/user")
    private ResponseEntity<Response> getByUser(){
        String userId = this.getUserInfo().getUserId();
        if(userId == null || userId.isEmpty()){
            return getResponse(Collections.emptyList());
        }
        var result = service.getByUserId(userId);
        return this.getResponse(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping("/{id}/person")
    private ResponseEntity<Response> savePerson(@PathVariable("id") String id, @RequestBody @Valid CoursePersonReq req){
        var result = service.savePerson(id, req).orElse(null);
        return getObjectResponse(result);
    }

    @PostMapping("/{id}/siswa")
    private ResponseEntity<Response> saveSiswa(@PathVariable("id") String id, @RequestBody @Valid CourseSiswaReq req){
        var result = service.saveSiswa(id, req).orElse(null);
        return getObjectResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid CourseReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid CourseReq request,
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
