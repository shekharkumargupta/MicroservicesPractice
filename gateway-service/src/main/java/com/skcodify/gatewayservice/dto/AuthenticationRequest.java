package com.skcodify.gatewayservice.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
