package com.pratwib.leaveapplicationapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private String adminId;
    private String name;
    private String phoneNumber;
    private String email;
}
