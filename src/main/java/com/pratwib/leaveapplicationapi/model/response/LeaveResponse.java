package com.pratwib.leaveapplicationapi.model.response;

import com.pratwib.leaveapplicationapi.constant.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponse {
    private String leaveId;
    private String employeeId;
    private String leaveTypeId;
    private String startDate;
    private String endDate;
    private Integer duration;
    private String notes;
    private String decisionBy;
    private ApprovalStatus approvalStatus;
    private String updatedAt;
    private String createdAt;
}
