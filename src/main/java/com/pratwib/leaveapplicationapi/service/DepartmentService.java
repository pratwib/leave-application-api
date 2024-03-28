package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Department;
import com.pratwib.leaveapplicationapi.model.request.DepartmentRequest;
import com.pratwib.leaveapplicationapi.model.response.DepartmentResponse;
import org.springframework.data.domain.Page;

public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest departmentRequest);

    Department getEntityById(String id);

    DepartmentResponse getById(String id);

    Page<DepartmentResponse> getAll(Integer page, Integer size);

    DepartmentResponse updateById(String id, DepartmentRequest departmentRequest);

    void softDeleteById(String id);
}
