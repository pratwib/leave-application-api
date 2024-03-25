package com.pratwib.leaveapplicationapi.model.entity;

import com.pratwib.leaveapplicationapi.constant.ApprovalStatus;
import com.pratwib.leaveapplicationapi.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = DbPath.LEAVE_SCHEMA)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @DateTimeFormat
    private LocalDate endDate;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "notes")
    private String notes;

    @Column(name = "decision_by")
    private String decisionBy;

    @Column(name = "approval_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;
}
