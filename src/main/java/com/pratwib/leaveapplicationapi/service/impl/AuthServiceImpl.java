package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.constant.ERole;
import com.pratwib.leaveapplicationapi.exception.ConflictException;
import com.pratwib.leaveapplicationapi.model.entity.*;
import com.pratwib.leaveapplicationapi.model.request.AuthRequest;
import com.pratwib.leaveapplicationapi.model.response.LoginResponse;
import com.pratwib.leaveapplicationapi.model.response.RegisterResponse;
import com.pratwib.leaveapplicationapi.security.JwtUtil;
import com.pratwib.leaveapplicationapi.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final RoleService roleService;
    private final AdminService adminService;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest) {
        try {
            Role role;
            if (authRequest.getNip() == null) {
                role = Role.builder()
                        .name(ERole.ADMIN)
                        .build();
            } else {
                role = Role.builder()
                        .name(ERole.EMPLOYEE)
                        .build();
            }
            roleService.getOrCreate(role);

            User user = User.builder()
                    .username(authRequest.getUsername())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(role)
                    .isActive(true)
                    .build();
            userService.create(user);

            if (authRequest.getNip() == null) {
                Admin admin = Admin.builder()
                        .name(authRequest.getName())
                        .phoneNumber(authRequest.getPhoneNumber())
                        .email(authRequest.getEmail())
                        .user(user)
                        .isActive(true)
                        .build();
                adminService.create(admin);
            } else {
                Employee employee = Employee.builder()
                        .nip(authRequest.getNip())
                        .name(authRequest.getName())
                        .phoneNumber(authRequest.getPhoneNumber())
                        .email(authRequest.getEmail())
                        .user(user)
                        .isActive(true)
                        .build();
                employeeService.create(employee);
            }

            return toRegisterResponse(user);
        } catch (
                DataIntegrityViolationException e) {
            throw new ConflictException("User already exist");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername().toLowerCase(),
                authRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return toLoginResponse(appUser, token);
    }

    private static LoginResponse toLoginResponse(AppUser appUser, String token) {
        return LoginResponse.builder()
                .username(appUser.getUsername())
                .role(appUser.getRole())
                .token(token)
                .build();
    }

    private static RegisterResponse toRegisterResponse(User user) {
        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().getName())
                .build();
    }
}
