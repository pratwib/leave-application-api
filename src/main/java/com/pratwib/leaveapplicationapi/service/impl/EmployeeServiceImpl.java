package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.model.entity.Department;
import com.pratwib.leaveapplicationapi.model.entity.Employee;
import com.pratwib.leaveapplicationapi.model.entity.User;
import com.pratwib.leaveapplicationapi.model.request.EmployeeRequest;
import com.pratwib.leaveapplicationapi.model.response.EmployeeResponse;
import com.pratwib.leaveapplicationapi.repository.EmployeeRepository;
import com.pratwib.leaveapplicationapi.service.DepartmentService;
import com.pratwib.leaveapplicationapi.service.EmployeeService;
import com.pratwib.leaveapplicationapi.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final UserService userService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEntityById(String id) {
        return employeeRepository.findByIdAndIsActive(id, true).orElseThrow(() ->
                new NotFoundException("Employee not found"));
    }

    @Override
    public Employee getEntityByUserId(String userId) {
        return employeeRepository.findByUser_IdAndIsActive(userId, true).orElseThrow(() ->
                new NotFoundException("Employee not found"));
    }

    @Override
    public EmployeeResponse getById(String id) {
        return toEmployeeResponse(getEntityById(id));
    }

    @Override
    public Page<EmployeeResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAllByIsActive(true, pageable);

        return employees.map(EmployeeServiceImpl::toEmployeeResponse);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public EmployeeResponse updateById(String id, EmployeeRequest employeeRequest) {
        Employee employee = getEntityById(id);

        Department department = departmentService.getEntityById(employeeRequest.getDepartmentId());

        employee.setNip(employeeRequest.getNip());
        employee.setName(employeeRequest.getName());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(department);
        employee.setPosition(employeeRequest.getPosition());
        employeeRepository.save(employee);

        return toEmployeeResponse(employee);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void softDeleteById(String id) {
        Employee employee = getEntityById(id);

        employee.setIsActive(false);
        employeeRepository.save(employee);

        User user = userService.getEntityById(employee.getUser().getId());
        userService.softDeleteById(user.getId());
    }

    private static EmployeeResponse toEmployeeResponse(Employee employee) {
        String departmentName = employee.getDepartment() != null ?
                employee.getDepartment().getName() : null;

        String position = employee.getPosition() != null ?
                employee.getPosition() : null;

        return EmployeeResponse.builder()
                .employeeId(employee.getId())
                .nip(employee.getNip())
                .name(employee.getName())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .departmentName(departmentName)
                .position(position)
                .build();
    }
}
