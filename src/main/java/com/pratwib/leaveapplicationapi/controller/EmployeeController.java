package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.EmployeeRequest;
import com.pratwib.leaveapplicationapi.model.response.CommonResponse;
import com.pratwib.leaveapplicationapi.model.response.EmployeeResponse;
import com.pratwib.leaveapplicationapi.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> getEmployees(
            @RequestParam(required = false) String departmentName
    ) {
        List<EmployeeResponse> employeeResponses = employeeService.getAll(departmentName);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<EmployeeResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved employees")
                        .data(employeeResponses)
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
}
