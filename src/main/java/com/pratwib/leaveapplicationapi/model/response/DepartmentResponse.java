package com.pratwib.leaveapplicationapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private String departmentId;
    private String departmentName;
}
