package com.doublevpartners.ticketmanagement.security.controller;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.PersonOutDto;
import com.doublevpartners.ticketmanagement.dto.response.ResponseDto;
import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;
import com.doublevpartners.ticketmanagement.security.dto.request.LogInDto;
import com.doublevpartners.ticketmanagement.security.service.AuthService;
import com.doublevpartners.ticketmanagement.security.util.SecurityConstants;
import com.doublevpartners.ticketmanagement.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación y registro", description = "Inicio de sesión y creación rápida de usuario")
public class AuthController {
    private final AuthService authService;
    private final PersonService personService;

    @Autowired
    public AuthController(AuthService authService, PersonService personService) {
        this.authService = authService;
        this.personService = personService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión con usuario y contraseña"
    )
    public ResponseEntity<ResponseDto<String>> logIn(@Validated @RequestBody LogInDto logInDto) throws BadCredentialsException {
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data(authService.authenticate(logInDto))
                        .message(SecurityConstants.SUCCESS_LOGIN)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }



}
