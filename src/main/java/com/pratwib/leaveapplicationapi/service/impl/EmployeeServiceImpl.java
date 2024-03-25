package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.model.entity.Employee;
import com.pratwib.leaveapplicationapi.repository.EmployeeRepository;
import com.pratwib.leaveapplicationapi.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }
}
