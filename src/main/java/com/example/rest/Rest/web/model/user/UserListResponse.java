package com.example.rest.Rest.web.model.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserListResponse {
    private List<UserResponse> users = new ArrayList<>();
}
