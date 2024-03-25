package com.pratwib.leaveapplicationapi.model.entity;

import com.pratwib.leaveapplicationapi.constant.DbPath;
import com.pratwib.leaveapplicationapi.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = DbPath.ROLE_SCHEMA)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ERole name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<User> users;
}
