package com.pratwib.leaveapplicationapi.model.request;

import com.pratwib.leaveapplicationapi.constant.ELeaveType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeRequest {
    @NotNull(message = "Leave type is required")
    private ELeaveType leaveTypeName;
}
