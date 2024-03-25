package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.request.AuthRequest;
import com.pratwib.leaveapplicationapi.model.response.LoginResponse;
import com.pratwib.leaveapplicationapi.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest authRequest);

    LoginResponse login(AuthRequest authRequest);
}
