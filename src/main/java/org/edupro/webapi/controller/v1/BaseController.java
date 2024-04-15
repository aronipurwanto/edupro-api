package org.edupro.webapi.controller.v1;

import org.edupro.webapi.constant.CommonConstant;
import org.edupro.webapi.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseController<T> {
    public ResponseEntity<Response> getResponse(Optional<T> result) {
        return result.<ResponseEntity<Response>>map(res -> ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(CommonConstant.SUCCESS_STATUS)
                        .data(res)
                        .build()
        )).orElseGet(() -> ResponseEntity.internalServerError().body(
                Response.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(CommonConstant.FAILED_STATUS)
                        .data(null)
                        .build()
        ));
    }

    public ResponseEntity<Response> get(List<T> result){
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(CommonConstant.SUCCESS_STATUS)
                        .data(result)
                        .build()
        );
    }
}
