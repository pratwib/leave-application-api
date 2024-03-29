package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.DepartmentRequest;
import com.pratwib.leaveapplicationapi.model.response.CommonResponse;
import com.pratwib.leaveapplicationapi.model.response.DepartmentResponse;
import com.pratwib.leaveapplicationapi.service.DepartmentService;
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
@RequestMapping(AppPath.DEPARTMENTS)
public class DepartmentController {
    private final DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.create(departmentRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<DepartmentResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new department")
                        .data(departmentResponse)
                        .build());
    }

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        DepartmentResponse departmentResponse = departmentService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DepartmentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved department")
                        .data(departmentResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        List<DepartmentResponse> departmentResponses = departmentService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<DepartmentResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved all departments")
                        .data(departmentResponses)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(AppPath.BY_ID)
    public ResponseEntity<?> updateDepartmentById(@PathVariable String id, @RequestBody @Valid DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.updateById(id, departmentRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<DepartmentResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated department")
                        .data(departmentResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(AppPath.BY_ID)
    public ResponseEntity<?> softDeleteDepartmentById(@PathVariable String id) {
        departmentService.softDeleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully soft deleted department")
                        .build());
    }
}
