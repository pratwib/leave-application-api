package com.pratwib.leaveapplicationapi.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "NIP is required")
    @Length(max = 10, message = "NIP must not exceed 10 characters")
    private String nip;
    @NotBlank(message = "Employee name is required")
    private String name;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Department ID is required")
    private String departmentId;
    @NotBlank(message = "Position is required")
    private String position;
}
