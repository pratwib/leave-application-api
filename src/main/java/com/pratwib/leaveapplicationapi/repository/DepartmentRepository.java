package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query(value = """
            SELECT * FROM m_department d
            WHERE d.id = :id AND d.is_active = :isActive
            """, nativeQuery = true)
    Optional<Department> findByIdAndIsActive(String id, Boolean isActive);

    @Query(value = """
            SELECT * FROM m_department d
            WHERE d.is_active = :isActive
            """, nativeQuery = true)
    List<Department> findAllByIsActive(Boolean isActive);
}
