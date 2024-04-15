package org.edupro.webapi.controller.v1;

import org.edupro.webapi.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/health")
    public ResponseEntity<Response> health(){
        return ResponseEntity.badRequest().body(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Success")
                        .data("ok")
                        .build()
        );
    }
}
