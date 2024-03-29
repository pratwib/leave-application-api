package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.constant.ERole;
import com.pratwib.leaveapplicationapi.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = """
            SELECT * FROM r_role r
            WHERE r.name = :name
            """, nativeQuery = true)
    Optional<Role> findByName(ERole name);
}
