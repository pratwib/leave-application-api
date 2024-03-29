package com.pratwib.leaveapplicationapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    @NotNull(message = "Leave type ID is required")
    private String leaveTypeId;
    @NotBlank(message = "Start date is required")
    private String startDate;
    @NotBlank(message = "End date is required")
    private String endDate;
    @NotNull(message = "Duration is required")
    private String notes;
}
