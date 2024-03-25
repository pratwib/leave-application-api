package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Admin;
import com.pratwib.leaveapplicationapi.model.entity.Employee;
import com.pratwib.leaveapplicationapi.model.request.AdminRequest;
import com.pratwib.leaveapplicationapi.model.response.AdminResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Employee create(Employee employee);

    Admin getEntityById(String id);

    AdminResponse getById(String id);

    Page<AdminResponse> getAll(Integer page, Integer size);

    AdminResponse updateById(String id, AdminRequest adminRequest);

    void softDeleteById(String id);
}
