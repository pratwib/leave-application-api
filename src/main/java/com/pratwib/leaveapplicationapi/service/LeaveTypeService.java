package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.LeaveType;
import com.pratwib.leaveapplicationapi.model.request.LeaveTypeRequest;
import com.pratwib.leaveapplicationapi.model.response.LeaveTypeResponse;
import org.springframework.data.domain.Page;

public interface LeaveTypeService {
    LeaveTypeResponse create(LeaveTypeRequest leaveTypeRequest);

    LeaveType getEntityById(String id);

    LeaveTypeResponse getById(String id);

    Page<LeaveTypeResponse> getAll(Integer page, Integer size);

    LeaveTypeResponse updateById(String id, LeaveTypeRequest leaveTypeRequest);

    void softDeleteById(String id);
}
