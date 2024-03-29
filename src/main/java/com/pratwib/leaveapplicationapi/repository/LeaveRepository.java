package com.pratwib.leaveapplicationapi.repository;

import com.pratwib.leaveapplicationapi.constant.ApprovalStatus;
import com.pratwib.leaveapplicationapi.constant.ELeaveType;
import com.pratwib.leaveapplicationapi.model.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, String> {
    @Query(value = """
            SELECT * FROM t_leave l
            JOIN m_leave_type lt ON l.leave_type_id = lt.id
            WHERE (l.employee_id = :employeeId OR lt.name = :leaveTypeName OR l.approval_status = :approvalStatus)
            """, nativeQuery = true)
    List<Leave> findAllByEmployee_IdOrLeaveType_NameOrApprovalStatus(String employeeId, ELeaveType leaveTypeName, ApprovalStatus approvalStatus);
}
