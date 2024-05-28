package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.CourseSectionReq;
import org.edupro.webapi.model.response.CourseSectionRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.CourseSectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/{courseId}")
@RequiredArgsConstructor
public class CourseSectionController extends BaseController<CourseSectionRes>{
    private final CourseSectionService service;

    @GetMapping("/topic")
    private ResponseEntity<Response> getTopic(@PathVariable("courseId") String id){
        var result = service.getByTopic(id);
        return getResponse(result);
    }

    @GetMapping("/section")
    private ResponseEntity<Response> get(@PathVariable("courseId") String courseId){
        var result = service.getByCourseId(courseId);
        return this.getResponse(result);
    }

    @GetMapping("/section/{id}")
    private ResponseEntity<Response> get(@PathVariable("courseId") String id, @PathVariable("sectionId") String sectionId){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping("/section")
    private ResponseEntity<Response> save(@PathVariable("courseId") String id, @RequestBody @Valid CourseSectionReq request){
        var result = service.save(id, request);
        return getResponse(result);
    }

    @PutMapping("/section/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid CourseSectionReq request, @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/section/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
