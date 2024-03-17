package com.example.rest.Rest.web.model;

import lombok.Data;

@Data
public class UpsertUserRequest {
    private String nickname;
    private String email;
    private String password;
}
