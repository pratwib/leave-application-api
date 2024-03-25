package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.model.entity.AppUser;
import com.pratwib.leaveapplicationapi.model.entity.User;
import com.pratwib.leaveapplicationapi.service.CredentialService;
import com.pratwib.leaveapplicationapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
    private final UserService userService;

    @Override
    public AppUser loadByUserId(String id) {
        User user = userService.getEntityById(id);

        return toAppUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getByUsername(username);

        return toAppUser(user);
    }

    private static AppUser toAppUser(User user) {
        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().getName())
                .build();
    }
}

