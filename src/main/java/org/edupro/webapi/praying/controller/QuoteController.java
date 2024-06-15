package org.edupro.webapi.praying.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name="keycloak")
public class QuoteController {
}
