package com.assignment.salonappointmentbookingsystem.repository;

import com.assignment.salonappointmentbookingsystem.model.Role;
import com.assignment.salonappointmentbookingsystem.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByEmail(String email);

    Boolean existsByRole(Role role);

    Boolean existsByEmail(String email);
}
