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
@Document(collection = "category")
@SuperBuilder(toBuilder = true)
public class Category {
    @Id
    private ObjectId id;
    private String categoryName;
    private Integer categoryPrice;
}
