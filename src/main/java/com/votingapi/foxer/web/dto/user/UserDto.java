package com.votingapi.foxer.web.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
}
