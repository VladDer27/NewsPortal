package com.example.rest.Rest.service;

import com.example.rest.Rest.model.User;
import com.example.rest.Rest.repository.DatabaseUserRepository;
import com.example.rest.Rest.utils.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final DatabaseUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public EncryptionService(DatabaseUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
}
