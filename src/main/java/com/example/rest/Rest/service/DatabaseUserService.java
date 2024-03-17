package com.example.rest.Rest.service;

import com.example.rest.Rest.exception.EntityNotFoundException;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.repository.DatabaseUserRepository;
import com.example.rest.Rest.utils.BeanUtils;
import com.example.rest.Rest.web.model.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseUserService implements UserService{

    private final DatabaseUserRepository userRepository;

    @Override
    public List<User> findAll(PaginationRequest request) {
        return userRepository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize())).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.
                format("Пользователь с ID: {0} не найден!", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
