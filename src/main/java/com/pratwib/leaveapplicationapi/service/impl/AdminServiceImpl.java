package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.model.entity.Admin;
import com.pratwib.leaveapplicationapi.repository.AdminRepository;
import com.pratwib.leaveapplicationapi.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }
}
