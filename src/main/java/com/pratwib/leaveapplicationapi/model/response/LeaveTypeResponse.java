package com.pratwib.leaveapplicationapi.model.response;

import com.pratwib.leaveapplicationapi.constant.ELeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeResponse {
    private String leaveTypeId;
    private ELeaveType leaveTypeName;
}
