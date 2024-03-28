package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.EmployeeRequest;
import com.pratwib.leaveapplicationapi.model.response.CommonResponse;
import com.pratwib.leaveapplicationapi.model.response.EmployeeResponse;
import com.pratwib.leaveapplicationapi.model.response.PagingResponse;
import com.pratwib.leaveapplicationapi.service.EmployeeService;
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
@RequestMapping(AppPath.EMPLOYEES)
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        EmployeeResponse employeeResponse = employeeService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<EmployeeResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved employee")
                        .data(employeeResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Page<EmployeeResponse> employeeResponses = employeeService.getAll(page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<EmployeeResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved all employees")
                        .data(employeeResponses.getContent())
                        .pagingResponse(toPagingResponse(page, size, employeeResponses))
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    @PutMapping(AppPath.BY_ID)
    public ResponseEntity<?> updateEmployeeById(@PathVariable String id, @RequestBody @Valid EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateById(id, employeeRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<EmployeeResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated employee")
                        .data(employeeResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    @DeleteMapping(AppPath.BY_ID)
    public ResponseEntity<?> softDeleteEmployeeById(@PathVariable String id) {
        employeeService.softDeleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully soft deleted employee")
                        .build());
    }

    private static PagingResponse toPagingResponse(Integer page, Integer size, Page<EmployeeResponse> employeeResponses) {
        return PagingResponse.builder()
                .currentPage(page)
                .totalPages(employeeResponses.getTotalPages())
                .size(size)
                .totalItems(employeeResponses.getTotalElements())
                .build();
    }
}
