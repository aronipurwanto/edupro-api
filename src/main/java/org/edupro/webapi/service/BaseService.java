package org.edupro.webapi.service;

import org.edupro.webapi.model.response.UserInfoRes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class BaseService {
    private static final String TOKEN_CLAIM_NAME = "preferred_username";

    public UserInfoRes getUserInfo(){
        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> claimTokens = Collections.emptyMap();
        claimTokens = ((JwtAuthenticationToken) authToken).getTokenAttributes();

        String dob = "";
        if (claimTokens.containsKey("DOB")) {
            dob = String.valueOf(claimTokens.get("DOB"));
        }
        String userName = (String) claimTokens.get(TOKEN_CLAIM_NAME);

        UserInfoRes result = new UserInfoRes();
        result.setUsername(userName);
        result.setUserId(userName);
        result.setDob(dob);

        return result;
    }
}
