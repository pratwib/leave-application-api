package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.Admin;
import com.pratwib.leaveapplicationapi.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByIdAndIsActive(String id, Boolean isActive);

    Page<Employee> findAllByIsActive(Boolean isActive, Pageable pageable);

    Optional<Employee> findByUser_IdAndIsActive(String userId, Boolean isActive);
}
