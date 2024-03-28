package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.model.entity.Department;
import com.pratwib.leaveapplicationapi.model.entity.Employee;
import com.pratwib.leaveapplicationapi.model.request.DepartmentRequest;
import com.pratwib.leaveapplicationapi.model.response.DepartmentResponse;
import com.pratwib.leaveapplicationapi.repository.DepartmentRepository;
import com.pratwib.leaveapplicationapi.service.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DepartmentResponse create(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.getDepartmentName())
                .isActive(true)
                .build();
        departmentRepository.save(department);

        return toDepartmentResponse(department);
    }

    @Override
    public Department getEntityById(String id) {
        return departmentRepository.findByIdAndIsActive(id, true).orElseThrow(() ->
                new NotFoundException("Department not found"));
    }

    @Override
    public DepartmentResponse getById(String id) {
        return toDepartmentResponse(getEntityById(id));
    }

    @Override
    public Page<DepartmentResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departments = departmentRepository.findAllByIsActive(true, pageable);

        return departments.map(DepartmentServiceImpl::toDepartmentResponse);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DepartmentResponse updateById(String id, DepartmentRequest departmentRequest) {
        Department department = getEntityById(id);

        department.setName(departmentRequest.getDepartmentName());
        departmentRepository.save(department);

        return toDepartmentResponse(department);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void softDeleteById(String id) {
        Department department = getEntityById(id);

        department.setIsActive(false);
        departmentRepository.save(department);
    }

    private static DepartmentResponse toDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .departmentId(department.getId())
                .departmentName(department.getName())
                .build();
    }
}
