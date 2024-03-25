package com.pratwib.leaveapplicationapi.model.entity;

import com.pratwib.leaveapplicationapi.constant.DbPath;
import com.pratwib.leaveapplicationapi.constant.EDepartment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = DbPath.DEPARTMENT_SCHEMA)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "department", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EDepartment name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
