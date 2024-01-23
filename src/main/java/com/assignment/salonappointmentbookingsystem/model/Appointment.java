package com.assignment.salonappointmentbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointment")
@SuperBuilder(toBuilder = true)
public class Appointment {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private ObjectId categoryId;
    private LocalDate bookingDate;
}
