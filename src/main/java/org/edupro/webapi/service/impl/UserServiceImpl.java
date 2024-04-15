package org.edupro.webapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.entity.UserEntity;
import org.edupro.webapi.model.request.ChangePasswordRequest;
import org.edupro.webapi.repository.UserRepo;
import org.edupro.webapi.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo repository;
    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }
}
