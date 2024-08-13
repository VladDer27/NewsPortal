package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.AOP.CheckUserAccess;
import com.example.rest.Rest.mapper.UserMapper;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.service.UserService;
import com.example.rest.Rest.web.model.PaginationRequest;
import com.example.rest.Rest.web.model.user.UpsertUserRequest;
import com.example.rest.Rest.web.model.user.UserListResponse;
import com.example.rest.Rest.web.model.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserListResponse> findAll(@Valid PaginationRequest request){
        return ResponseEntity.ok(userMapper.userListToResponse(userService.findAll(request)));
    }

    @GetMapping("/{id}")
    @CheckUserAccess
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }


    @PutMapping("/{id}")
    @CheckUserAccess
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UpsertUserRequest request){
        User updatedUser = userService.update(userMapper.requestToUserWithId(id, request));

        return ResponseEntity.ok().body(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    @CheckUserAccess
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
