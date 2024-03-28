package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByIdAndIsActive(String id, Boolean isActive);

    Page<Admin> findAllByIsActive(Boolean isActive, Pageable pageable);

    Optional<Admin> findByUser_IdAndIsActive(String userId, Boolean isActive);
}
