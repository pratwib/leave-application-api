package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = """
            SELECT * FROM m_employee e
            WHERE e.id = :id AND e.is_active = :isActive
            """, nativeQuery = true)
    Optional<Employee> findByIdAndIsActive(String id, Boolean isActive);

    @Query(value = """
            SELECT * FROM m_employee e
            WHERE e.user_id = :userId AND e.is_active = :isActive
            """, nativeQuery = true)
    Optional<Employee> findByUser_IdAndIsActive(String userId, Boolean isActive);

    List<Employee> findAllByDepartment_NameOrIsActive(String departmentName, Boolean isActive);
}
