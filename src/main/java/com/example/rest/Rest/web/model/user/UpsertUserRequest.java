package com.example.rest.Rest.web.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertUserRequest {

    @NotNull
    @Size(min = 3, max = 30, message = "Min nickname size is: {min}. Max nickname size is: {max}")
    private String nickname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
