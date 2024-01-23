package com.assignment.salonappointmentbookingsystem.controller;

import com.assignment.salonappointmentbookingsystem.annotations.IsAdmin;
import com.assignment.salonappointmentbookingsystem.request.CategoryRequest;
import com.assignment.salonappointmentbookingsystem.response.CategoryResponse;
import com.assignment.salonappointmentbookingsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    @IsAdmin
    ResponseEntity<CategoryResponse> add(@RequestBody CategoryRequest categoryRequest) {
        if (categoryRequest.getCategoryName().isBlank() || Objects.isNull(categoryRequest.getCategoryPrice())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name or price can not be blank");
        }
        return new ResponseEntity<>(categoryService.add(categoryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    @IsAdmin
    ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest categoryRequest, @PathVariable String categoryId) {
        if (categoryRequest.getCategoryName().isBlank() || Objects.isNull(categoryRequest.getCategoryPrice())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name or price can not be blank");
        }
        return new ResponseEntity<>(categoryService.update(categoryRequest, categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    @IsAdmin
    ResponseEntity<String> delete(@PathVariable String categoryId) {
        if (Objects.isNull(categoryId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category id can not be blank");
        }
        categoryService.delete(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

}
