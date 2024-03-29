package com.pratwib.leaveapplicationapi.controller;

import com.pratwib.leaveapplicationapi.constant.AppPath;
import com.pratwib.leaveapplicationapi.model.request.AdminRequest;
import com.pratwib.leaveapplicationapi.model.response.AdminResponse;
import com.pratwib.leaveapplicationapi.model.response.CommonResponse;
import com.pratwib.leaveapplicationapi.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(AppPath.ADMINS)
public class AdminController {
    private final AdminService adminService;

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getAdminById(@PathVariable String id) {
        AdminResponse adminResponse = adminService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved admin")
                        .data(adminResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        List<AdminResponse> adminResponses = adminService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<AdminResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully retrieved all admins")
                        .data(adminResponses)
                        .build());
    }

    @PutMapping(AppPath.BY_ID)
    public ResponseEntity<?> updateAdminById(@PathVariable String id, @RequestBody @Valid AdminRequest adminRequest) {
        AdminResponse adminResponse = adminService.updateById(id, adminRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated admin")
                        .data(adminResponse)
                        .build());
    }

    @DeleteMapping(AppPath.BY_ID)
    public ResponseEntity<?> softDeleteAdminById(@PathVariable String id) {
        adminService.softDeleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully soft deleted admin")
                        .build());
    }
}
