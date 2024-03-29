package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Leave;
import com.pratwib.leaveapplicationapi.model.request.LeaveRequest;
import com.pratwib.leaveapplicationapi.model.response.LeaveResponse;

import java.util.List;

public interface LeaveService {
    LeaveResponse apply(LeaveRequest leaveRequest);

    Leave getEntityById(String id);

    LeaveResponse getById(String id);

    List<LeaveResponse> getAll(String employeeId, String leaveType, String approvalStatus);

    LeaveResponse approve(String id);

    LeaveResponse reject(String id);
}
