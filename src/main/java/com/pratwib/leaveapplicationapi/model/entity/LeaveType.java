package com.pratwib.leaveapplicationapi.model.entity;

import com.pratwib.leaveapplicationapi.constant.DbPath;
import com.pratwib.leaveapplicationapi.constant.EDepartment;
import com.pratwib.leaveapplicationapi.constant.ELeaveType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = DbPath.LEAVE_TYPE_SCHEMA)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "leave_type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ELeaveType name;

    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.PERSIST)
    private List<Leave> leaves;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
