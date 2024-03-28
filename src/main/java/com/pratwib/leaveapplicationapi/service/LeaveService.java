package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.Leave;
import com.pratwib.leaveapplicationapi.model.request.LeaveRequest;
import com.pratwib.leaveapplicationapi.model.response.LeaveResponse;
import org.springframework.data.domain.Page;

public interface LeaveService {
    LeaveResponse apply(LeaveRequest leaveRequest);

    Leave getEntityById(String id);

    LeaveResponse getById(String id);

    Page<LeaveResponse> getAll(Integer page, Integer size);

    LeaveResponse approve(String id);

    LeaveResponse reject(String id);
}
