package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.model.entity.Role;
import com.pratwib.leaveapplicationapi.repository.RoleRepository;
import com.pratwib.leaveapplicationapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void getOrCreate(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());

        optionalRole.orElseGet(() -> roleRepository.saveAndFlush(role));
    }
}
