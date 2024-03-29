package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, String> {
    @Query(value = """
            SELECT * FROM m_leave_type lt
            WHERE lt.id = :id AND lt.is_active = :isActive
            """, nativeQuery = true)
    Optional<LeaveType> findByIdAndIsActive(String id, Boolean isActive);

    @Query(value = """
            SELECT * FROM m_leave_type lt
            WHERE lt.is_active = :isActive
            """, nativeQuery = true)
    List<LeaveType> findAllByIsActive(Boolean isActive);
}
