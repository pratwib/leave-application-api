package com.pratwib.leaveapplicationapi.model.response;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String employeeId;
    private String nip;
    private String name;
    private String phoneNumber;
    private String email;
    private String departmentName;
    private String position;
}
