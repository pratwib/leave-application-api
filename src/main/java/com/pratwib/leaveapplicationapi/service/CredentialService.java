package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CredentialService extends UserDetailsService {
    AppUser loadByUserId(String id);
}
