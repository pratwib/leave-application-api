package com.pratwib.leaveapplicationapi.model.response;

import com.pratwib.leaveapplicationapi.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String username;
    private ERole role;
}
