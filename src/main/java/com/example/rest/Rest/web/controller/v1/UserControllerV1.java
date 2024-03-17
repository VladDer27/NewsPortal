package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.mapper.UserMapper;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.service.UserService;
import com.example.rest.Rest.web.model.UpsertUserRequest;
import com.example.rest.Rest.web.model.UserListResponse;
import com.example.rest.Rest.web.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(){
        return ResponseEntity.ok(userMapper.userListToResponse(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UpsertUserRequest request){
        User newUser = userService.save(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UpsertUserRequest request){
        User updatedUser = userService.update(userMapper.requestToUserWithId(id, request));

        return ResponseEntity.ok().body(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
