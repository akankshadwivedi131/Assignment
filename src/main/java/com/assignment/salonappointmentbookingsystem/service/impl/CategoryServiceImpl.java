package com.assignment.salonappointmentbookingsystem.service.impl;

import com.assignment.salonappointmentbookingsystem.mapper.CommonMapper;
import com.assignment.salonappointmentbookingsystem.model.Category;
import com.assignment.salonappointmentbookingsystem.repository.CategoryRepository;
import com.assignment.salonappointmentbookingsystem.request.CategoryRequest;
import com.assignment.salonappointmentbookingsystem.response.CategoryResponse;
import com.assignment.salonappointmentbookingsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CommonMapper commonMapper;

    @Override
    public CategoryResponse add(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category Exists please try another one.");
        }
        return commonMapper.categoryToCategoryResponse(categoryRepository.save(commonMapper.categoryRequestToCategory(categoryRequest)));
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, String categoryId) {
        Category category = categoryRepository.findById(new ObjectId(categoryId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category Exists please try another one.");
        }
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setCategoryPrice(categoryRequest.getCategoryPrice());
        return commonMapper.categoryToCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void delete(String categoryId) {
        categoryRepository.deleteById(new ObjectId(categoryId));
    }
}
