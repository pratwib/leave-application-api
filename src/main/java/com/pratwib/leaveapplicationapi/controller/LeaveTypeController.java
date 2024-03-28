package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.LeaveTypeRequest;
import com.pratwib.leaveapplicationapi.model.response.CommonResponse;
import com.pratwib.leaveapplicationapi.model.response.LeaveTypeResponse;
import com.pratwib.leaveapplicationapi.model.response.PagingResponse;
import com.pratwib.leaveapplicationapi.service.LeaveTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(AppPath.LEAVE_TYPES)
public class LeaveTypeController {
    private final LeaveTypeService leaveTypeService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createLeaveType(@RequestBody @Valid LeaveTypeRequest leaveTypeRequest) {
        LeaveTypeResponse leaveTypeResponse = leaveTypeService.create(leaveTypeRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<LeaveTypeResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new leaveType")
                        .data(leaveTypeResponse)
                        .build());
    }

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getLeaveTypeById(@PathVariable String id) {
        LeaveTypeResponse leaveTypeResponse = leaveTypeService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LeaveTypeResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved leaveType")
                        .data(leaveTypeResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllLeaveTypes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Page<LeaveTypeResponse> leaveTypeResponses = leaveTypeService.getAll(page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<LeaveTypeResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved all leaveTypes")
                        .data(leaveTypeResponses.getContent())
                        .pagingResponse(toPagingResponse(page, size, leaveTypeResponses))
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(AppPath.BY_ID)
    public ResponseEntity<?> updateLeaveTypeById(@PathVariable String id, @RequestBody @Valid LeaveTypeRequest leaveTypeRequest) {
        LeaveTypeResponse leaveTypeResponse = leaveTypeService.updateById(id, leaveTypeRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LeaveTypeResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated leaveType")
                        .data(leaveTypeResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(AppPath.BY_ID)
    public ResponseEntity<?> softDeleteLeaveTypeById(@PathVariable String id) {
        leaveTypeService.softDeleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully soft deleted leaveType")
                        .build());
    }

    private static PagingResponse toPagingResponse(Integer page, Integer size, Page<LeaveTypeResponse> leaveTypeResponses) {
        return PagingResponse.builder()
                .currentPage(page)
                .totalPages(leaveTypeResponses.getTotalPages())
                .size(size)
                .totalItems(leaveTypeResponses.getTotalElements())
                .build();
    }
}
