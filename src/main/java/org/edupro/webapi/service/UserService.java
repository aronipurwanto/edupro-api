package org.edupro.webapi.service;

import org.edupro.webapi.model.request.ChangePasswordRequest;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
