package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.model.entity.Department;
import com.pratwib.leaveapplicationapi.model.request.DepartmentRequest;
import com.pratwib.leaveapplicationapi.model.response.DepartmentResponse;
import com.pratwib.leaveapplicationapi.repository.DepartmentRepository;
import com.pratwib.leaveapplicationapi.service.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<DepartmentResponse> getAll() {
        List<Department> departments = departmentRepository.findAllByIsActive(true);

        return departments.stream().map(DepartmentServiceImpl::toDepartmentResponse).toList();
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
