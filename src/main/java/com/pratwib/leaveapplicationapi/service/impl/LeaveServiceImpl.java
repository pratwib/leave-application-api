package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.constant.ApprovalStatus;
import com.pratwib.leaveapplicationapi.constant.ELeaveType;
import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.exception.NotInPendingStatusException;
import com.pratwib.leaveapplicationapi.model.entity.*;
import com.pratwib.leaveapplicationapi.model.request.LeaveRequest;
import com.pratwib.leaveapplicationapi.model.response.LeaveResponse;
import com.pratwib.leaveapplicationapi.repository.LeaveRepository;
import com.pratwib.leaveapplicationapi.service.AdminService;
import com.pratwib.leaveapplicationapi.service.EmployeeService;
import com.pratwib.leaveapplicationapi.service.LeaveService;
import com.pratwib.leaveapplicationapi.service.LeaveTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeService employeeService;
    private final AdminService adminService;
    private final LeaveTypeService leaveTypeService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LeaveResponse apply(LeaveRequest leaveRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((AppUser) authentication.getPrincipal()).getId();

        Employee employee = employeeService.getEntityByUserId(userId);

        LeaveType leaveType = leaveTypeService.getEntityById(leaveRequest.getLeaveTypeId());

        LocalDate startDate = LocalDate.parse(leaveRequest.getStartDate());
        LocalDate endDate = LocalDate.parse(leaveRequest.getEndDate());
        long duration = ChronoUnit.DAYS.between(startDate, endDate);

        Leave leave = Leave.builder()
                .employee(employee)
                .leaveType(leaveType)
                .startDate(startDate)
                .endDate(endDate)
                .duration(Math.toIntExact(duration))
                .notes(leaveRequest.getNotes())
                .approvalStatus(ApprovalStatus.PENDING)
                .createdAt(System.currentTimeMillis())
                .build();
        leaveRepository.save(leave);

        return toLeaveResponse(leave);
    }

    @Override
    public Leave getEntityById(String id) {
        return leaveRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Leave data not found"));
    }

    @Override
    public LeaveResponse getById(String id) {
        return toLeaveResponse(getEntityById(id));
    }

    @Override
    public List<LeaveResponse> getAll(String employeeId, String leaveType, String approvalStatus) {
        ELeaveType searchedLeaveType = leaveType != null ? ELeaveType.valueOf(leaveType.toUpperCase()) : null;
        ApprovalStatus searchedApprovalStatus = approvalStatus != null ? ApprovalStatus.valueOf(approvalStatus.toUpperCase()) : null;

        List<Leave> leaves;
        if (employeeId == null && leaveType == null && approvalStatus == null) {
            leaves = leaveRepository.findAll();
        } else {
            leaves = leaveRepository.findAllByEmployee_IdOrLeaveType_NameOrApprovalStatus(employeeId, searchedLeaveType, searchedApprovalStatus);
        }

        return leaves.stream().map(LeaveServiceImpl::toLeaveResponse).toList();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LeaveResponse approve(String id) {
        Leave leave = getEntityById(id);

        if (leave.getApprovalStatus() != ApprovalStatus.PENDING) {
            throw new NotInPendingStatusException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((AppUser) authentication.getPrincipal()).getId();

        Admin admin = adminService.getEntityByUserId(userId);

        leave.setDecisionBy(admin.getName());
        leave.setApprovalStatus(ApprovalStatus.APPROVED);
        leave.setUpdatedAt(System.currentTimeMillis());
        leaveRepository.save(leave);

        return toLeaveResponse(leave);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LeaveResponse reject(String id) {
        Leave leave = getEntityById(id);

        if (leave.getApprovalStatus() != ApprovalStatus.PENDING) {
            throw new NotInPendingStatusException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((AppUser) authentication.getPrincipal()).getId();

        Admin admin = adminService.getEntityByUserId(userId);

        leave.setDecisionBy(admin.getName());
        leave.setApprovalStatus(ApprovalStatus.REJECTED);
        leave.setUpdatedAt(System.currentTimeMillis());
        leaveRepository.save(leave);

        return toLeaveResponse(leave);
    }

    private static LeaveResponse toLeaveResponse(Leave leave) {
        return LeaveResponse.builder()
                .leaveId(leave.getId())
                .employeeId(leave.getEmployee().getId())
                .leaveTypeId(leave.getLeaveType().getId())
                .startDate(leave.getStartDate().toString())
                .endDate(leave.getEndDate().toString())
                .duration(leave.getDuration())
                .notes(leave.getNotes())
                .decisionBy(leave.getDecisionBy())
                .approvalStatus(leave.getApprovalStatus())
                .updatedAt(leave.getUpdatedAt() != null ? leave.getUpdatedAt().toString() : null)
                .createdAt(leave.getCreatedAt().toString())
                .build();
    }
}
