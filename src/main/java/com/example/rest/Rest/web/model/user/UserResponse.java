package com.example.rest.Rest.web.model.user;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String nickname;
    private String email;
}
