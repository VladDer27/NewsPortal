package com.example.rest.Rest.web.model.user;

import lombok.Data;

@Data
public class UpsertUserRequest {
    private String nickname;
    private String email;
    private String password;
}
