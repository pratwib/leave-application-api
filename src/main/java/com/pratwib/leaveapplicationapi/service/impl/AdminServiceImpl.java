package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.model.entity.Admin;
import com.pratwib.leaveapplicationapi.model.entity.User;
import com.pratwib.leaveapplicationapi.model.request.AdminRequest;
import com.pratwib.leaveapplicationapi.model.response.AdminResponse;
import com.pratwib.leaveapplicationapi.repository.AdminRepository;
import com.pratwib.leaveapplicationapi.service.AdminService;
import com.pratwib.leaveapplicationapi.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getEntityById(String id) {
        return adminRepository.findByIdAndIsActive(id, true).orElseThrow(() ->
                new NotFoundException("Admin not found"));
    }

    @Override
    public Admin getEntityByUserId(String userId) {
        return adminRepository.findByUser_IdAndIsActive(userId, true).orElseThrow(() ->
                new NotFoundException("Admin not found"));
    }

    @Override
    public AdminResponse getById(String id) {
        return toAdminResponse(getEntityById(id));
    }

    @Override
    public List<AdminResponse> getAll() {
        List<Admin> admins = adminRepository.findAllByIsActive(true);

        return admins.stream().map(AdminServiceImpl::toAdminResponse).toList();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public AdminResponse updateById(String id, AdminRequest adminRequest) {
        Admin admin = getEntityById(id);

        admin.setName(adminRequest.getName());
        admin.setPhoneNumber(adminRequest.getPhoneNumber());
        admin.setEmail(adminRequest.getEmail());
        adminRepository.save(admin);

        return toAdminResponse(admin);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void softDeleteById(String id) {
        Admin admin = getEntityById(id);

        admin.setIsActive(false);
        adminRepository.save(admin);

        User user = userService.getEntityById(admin.getUser().getId());
        userService.softDeleteById(user.getId());
    }

    private static AdminResponse toAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .adminId(admin.getId())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .email(admin.getEmail())
                .build();
    }
}
