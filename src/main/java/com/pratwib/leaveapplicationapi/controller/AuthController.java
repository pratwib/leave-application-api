package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.AuthRequest;
import com.pratwib.leaveapplicationapi.model.response.CommonResponse;
import com.pratwib.leaveapplicationapi.model.response.LoginResponse;
import com.pratwib.leaveapplicationapi.model.response.RegisterResponse;
import com.pratwib.leaveapplicationapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(AppPath.SIGNUP)
    public ResponseEntity<?> register(@RequestBody @Valid AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.register(authRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully signed up new user")
                        .data(registerResponse)
                        .build());
    }

    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        LoginResponse loginResponse = authService.login(authRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully logged in")
                        .data(loginResponse)
                        .build());
    }
}
