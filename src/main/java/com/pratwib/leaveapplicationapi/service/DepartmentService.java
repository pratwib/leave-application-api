package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Department;
import com.pratwib.leaveapplicationapi.model.request.DepartmentRequest;
import com.pratwib.leaveapplicationapi.model.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest departmentRequest);

    Department getEntityById(String id);

    DepartmentResponse getById(String id);

    List<DepartmentResponse> getAll();

    DepartmentResponse updateById(String id, DepartmentRequest departmentRequest);

    void softDeleteById(String id);
}
