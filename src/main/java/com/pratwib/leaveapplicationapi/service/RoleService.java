package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Role;

public interface RoleService {
    void getOrCreate(Role role);
}
