package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = """
            SELECT * FROM r_user u
            WHERE u.username = :username
            """, nativeQuery = true)
    Optional<User> findByUsername(String username);
}
