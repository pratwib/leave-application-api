package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.model.entity.LeaveType;
import com.pratwib.leaveapplicationapi.model.request.LeaveTypeRequest;
import com.pratwib.leaveapplicationapi.model.response.LeaveTypeResponse;
import com.pratwib.leaveapplicationapi.repository.LeaveTypeRepository;
import com.pratwib.leaveapplicationapi.service.LeaveTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveTypeServiceImpl implements LeaveTypeService {
    private final LeaveTypeRepository leaveTypeRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LeaveTypeResponse create(LeaveTypeRequest leaveTypeRequest) {
        LeaveType leaveType = LeaveType.builder()
                .name(leaveTypeRequest.getLeaveTypeName())
                .isActive(true)
                .build();
        leaveTypeRepository.save(leaveType);

        return toLeaveTypeResponse(leaveType);
    }

    @Override
    public LeaveType getEntityById(String id) {
        return leaveTypeRepository.findByIdAndIsActive(id, true).orElseThrow(() ->
                new NotFoundException("Leave type not found"));
    }

    @Override
    public LeaveTypeResponse getById(String id) {
        return toLeaveTypeResponse(getEntityById(id));
    }

    @Override
    public Page<LeaveTypeResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LeaveType> leaveTypes = leaveTypeRepository.findAllByIsActive(true, pageable);

        return leaveTypes.map(LeaveTypeServiceImpl::toLeaveTypeResponse);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LeaveTypeResponse updateById(String id, LeaveTypeRequest leaveTypeRequest) {
        LeaveType leaveType = getEntityById(id);

        leaveType.setName(leaveTypeRequest.getLeaveTypeName());
        leaveTypeRepository.save(leaveType);

        return toLeaveTypeResponse(leaveType);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void softDeleteById(String id) {
        LeaveType leaveType = getEntityById(id);

        leaveType.setIsActive(false);
        leaveTypeRepository.save(leaveType);
    }

    private static LeaveTypeResponse toLeaveTypeResponse(LeaveType leaveType) {
        return LeaveTypeResponse.builder()
                .leaveTypeId(leaveType.getId())
                .leaveTypeName(leaveType.getName())
                .build();
    }
}