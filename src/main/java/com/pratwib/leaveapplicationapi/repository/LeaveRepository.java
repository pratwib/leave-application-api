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
    List<Leave> findAllByEmployee_IdOrLeaveType_NameOrApprovalStatus(String employeeId, ELeaveType leaveTypeName, ApprovalStatus approvalStatus);
}
