package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.LeaveType;
import com.pratwib.leaveapplicationapi.model.request.LeaveTypeRequest;
import com.pratwib.leaveapplicationapi.model.response.LeaveTypeResponse;

import java.util.List;

public interface LeaveTypeService {
    LeaveTypeResponse create(LeaveTypeRequest leaveTypeRequest);

    LeaveType getEntityById(String id);

    LeaveTypeResponse getById(String id);

    List<LeaveTypeResponse> getAll();

    LeaveTypeResponse updateById(String id, LeaveTypeRequest leaveTypeRequest);

    void softDeleteById(String id);
}
