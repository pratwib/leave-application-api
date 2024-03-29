package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query(value = """
            SELECT * FROM m_admin a
            WHERE a.id = :id AND a.is_active = :isActive
            """, nativeQuery = true)
    Optional<Admin> findByIdAndIsActive(String id, Boolean isActive);

    @Query(value = """
            SELECT * FROM m_admin a
            WHERE a.is_active = :isActive
            """, nativeQuery = true)
    List<Admin> findAllByIsActive(Boolean isActive);

    @Query(value = """
            SELECT * FROM m_admin a
            JOIN users u ON a.user_id = u.id
            WHERE u.id = :userId AND a.is_active = :isActive
            """, nativeQuery = true)
    Optional<Admin> findByUser_IdAndIsActive(String userId, Boolean isActive);
}
