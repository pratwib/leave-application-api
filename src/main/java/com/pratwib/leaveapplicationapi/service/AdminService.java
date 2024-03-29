package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Admin;
import com.pratwib.leaveapplicationapi.model.request.AdminRequest;
import com.pratwib.leaveapplicationapi.model.response.AdminResponse;

import java.util.List;

public interface AdminService {
    Admin create(Admin admin);

    Admin getEntityById(String id);

    Admin getEntityByUserId(String userId);


    AdminResponse getById(String id);

    List<AdminResponse> getAll();

    AdminResponse updateById(String id, AdminRequest adminRequest);

    void softDeleteById(String id);

}
