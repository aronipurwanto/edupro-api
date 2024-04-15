package org.edupro.webapi.controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.model.request.AuthenticationRequest;
import org.edupro.webapi.model.request.RegisterRequest;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest request) {
        var result = service.register(request);
        return ResponseEntity.ok(
                new Response(HttpStatus.OK.value(), MessageApp.SUCCESS,result)
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthenticationRequest request) {
        var result = service.authenticate(request);
        return ResponseEntity.ok(
                new Response(HttpStatus.OK.value(), MessageApp.SUCCESS,result)
        );
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/accessDenied")
    public ResponseEntity<Response> accessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Response(HttpStatus.FORBIDDEN.value(), MessageApp.FORBIDDEN,null ));
    }
}
