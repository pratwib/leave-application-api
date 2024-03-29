package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Employee;
import com.pratwib.leaveapplicationapi.model.request.EmployeeRequest;
import com.pratwib.leaveapplicationapi.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee getEntityById(String id);

    Employee getEntityByUserId(String userId);

    EmployeeResponse getById(String id);

    List<EmployeeResponse> getAll(String departmentName);

    EmployeeResponse updateById(String id, EmployeeRequest employeeRequest);

    void softDeleteById(String id);
}
