package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.LeaveType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, String> {
    Optional<LeaveType> findByIdAndIsActive(String id, Boolean isActive);

    Page<LeaveType> findAllByIsActive(Boolean isActive, Pageable pageable);
}
