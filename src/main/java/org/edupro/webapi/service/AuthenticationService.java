package org.edupro.webapi.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edupro.webapi.model.request.AuthenticationRequest;
import org.edupro.webapi.model.request.RegisterRequest;
import org.edupro.webapi.model.response.AuthenticationResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
