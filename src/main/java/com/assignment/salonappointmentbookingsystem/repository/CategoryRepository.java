package com.assignment.salonappointmentbookingsystem.repository;

import com.assignment.salonappointmentbookingsystem.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Boolean existsByCategoryName(String categoryName);
}
