package com.assignment.salonappointmentbookingsystem.service;

import com.assignment.salonappointmentbookingsystem.request.CategoryRequest;
import com.assignment.salonappointmentbookingsystem.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest categoryRequest);

    CategoryResponse update(CategoryRequest categoryRequest, String categoryId);

    void delete(String categoryId);
}
