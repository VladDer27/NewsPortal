package com.example.rest.Rest.service;

import com.example.rest.Rest.exception.AlreadyExistException;
import com.example.rest.Rest.exception.EntityNotFoundException;
import com.example.rest.Rest.model.Category;
import com.example.rest.Rest.repository.DatabaseCategoryRepository;
import com.example.rest.Rest.utils.BeanUtils;
import com.example.rest.Rest.web.model.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseCategoryService implements CategoryService{

    private final DatabaseCategoryRepository categoryRepository;


    @Override
    public List<Category> findAll(PaginationRequest request) {
        return categoryRepository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize())).getContent();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.
                format("Категория с ID: {0} не найдена!", id)));
    }

    @Override
    public Category save(Category category) {
        if(categoryRepository.existsCategoryByName(category.getName())){
            throw new AlreadyExistException(MessageFormat.format("Категория с именем: {0} уже существует!", category.getName()));
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        if(categoryRepository.existsCategoryByName(category.getName())){
            throw new AlreadyExistException(MessageFormat.format("Категория с именем: {0} уже существует!", category.getName()));
        }
        Category existedCategory = findById(category.getId());
        BeanUtils.copyNonNullProperties(category, existedCategory);
        return categoryRepository.save(existedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
