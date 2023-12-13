package com.votingapi.foxer.web.dto.auth;

import lombok.Data;

@Data
public class JwtToken {
    private String token;
    private String type;
    private String algorithm;
    private Long expiresAt;
}