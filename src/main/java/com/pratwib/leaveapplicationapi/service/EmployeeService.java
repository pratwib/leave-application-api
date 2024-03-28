package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Employee;
import com.pratwib.leaveapplicationapi.model.request.EmployeeRequest;
import com.pratwib.leaveapplicationapi.model.response.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee getEntityById(String id);

    Employee getEntityByUserId(String userId);

    EmployeeResponse getById(String id);

    Page<EmployeeResponse> getAll(Integer page, Integer size);

    EmployeeResponse updateById(String id, EmployeeRequest employeeRequest);

    void softDeleteById(String id);
}
