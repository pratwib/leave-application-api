package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.LeaveRequest;
import com.pratwib.leaveapplicationapi.model.response.*;
import com.pratwib.leaveapplicationapi.model.response.LeaveResponse;
import com.pratwib.leaveapplicationapi.service.LeaveService;
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
@RequestMapping(AppPath.LEAVES)
public class LeaveController {
    private final LeaveService leaveService;

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    @PostMapping
    public ResponseEntity<?> applyLeave(@RequestBody @Valid LeaveRequest leaveRequest) {
        LeaveResponse leaveResponse = leaveService.apply(leaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<LeaveResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new leave data")
                        .data(leaveResponse)
                        .build());
    }

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getLeaveById(@PathVariable String id) {
        LeaveResponse leaveResponse = leaveService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LeaveResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved leave data")
                        .data(leaveResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllLeaves(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Page<LeaveResponse> leaveResponses = leaveService.getAll(page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<LeaveResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved all leaves")
                        .data(leaveResponses.getContent())
                        .pagingResponse(toPagingResponse(page, size, leaveResponses))
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(AppPath.BY_ID + "/approve")
    public ResponseEntity<?> approveLeaveById(@PathVariable String id) {
        LeaveResponse leaveResponse = leaveService.approve(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LeaveResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully approved leave data")
                        .data(leaveResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(AppPath.BY_ID + "/reject")
    public ResponseEntity<?> rejectLeaveById(@PathVariable String id) {
        LeaveResponse leaveResponse = leaveService.reject(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LeaveResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully rejected leave data")
                        .data(leaveResponse)
                        .build());
    }

    private static PagingResponse toPagingResponse(Integer page, Integer size, Page<LeaveResponse> leaveResponses) {
        return PagingResponse.builder()
                .currentPage(page)
                .totalPages(leaveResponses.getTotalPages())
                .size(size)
                .totalItems(leaveResponses.getTotalElements())
                .build();
    }
}
