package com.example.rest.Rest.mapper;

import com.example.rest.Rest.model.User;
import com.example.rest.Rest.web.model.UpsertUserRequest;
import com.example.rest.Rest.web.model.UserListResponse;
import com.example.rest.Rest.web.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface UserMapper {
    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUserWithId(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userListToResponse(List<User> users){
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream().map(this::userToResponse).collect(Collectors.toList()));

        return response;
    }
}
