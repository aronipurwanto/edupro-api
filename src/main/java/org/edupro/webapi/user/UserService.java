package org.edupro.webapi.user;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordReq request, Principal connectedUser);
}
