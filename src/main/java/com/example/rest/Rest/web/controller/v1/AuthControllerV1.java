package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.mapper.UserMapper;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.service.AuthService;
import com.example.rest.Rest.service.EncryptionService;
import com.example.rest.Rest.utils.UserValidator;
import com.example.rest.Rest.web.model.user.LoginRequest;
import com.example.rest.Rest.web.model.user.LoginResponse;
import com.example.rest.Rest.web.model.user.UpsertUserRequest;
import com.example.rest.Rest.web.model.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {

    private final UserValidator userValidator;

    private final UserMapper userMapper;

    private final EncryptionService encryptionService;

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(authService.attemptLogin(request.getLogin(), request.getPassword()));
    }

    @PostMapping("/registration")
    public ResponseEntity<UserResponse> registration(@RequestBody @Valid UpsertUserRequest request,
                                                     BindingResult bindingResult){
        User newUser = userMapper.requestToUser(request);
        userValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        encryptionService.register(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(newUser));
    }
}
