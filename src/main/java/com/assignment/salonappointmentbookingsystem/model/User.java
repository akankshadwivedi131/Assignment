package com.assignment.salonappointmentbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
@SuperBuilder(toBuilder = true)
public class User {
    @Id
    private ObjectId id;
    private String email;
    private String password;
    private String fullName;
    private Role role;
}
