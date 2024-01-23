package com.assignment.salonappointmentbookingsystem.mapper;


import com.assignment.salonappointmentbookingsystem.annotations.EncodedMapping;
import com.assignment.salonappointmentbookingsystem.annotations.PasswordEncoderMapper;
import com.assignment.salonappointmentbookingsystem.model.Appointment;
import com.assignment.salonappointmentbookingsystem.model.Category;
import com.assignment.salonappointmentbookingsystem.model.User;
import com.assignment.salonappointmentbookingsystem.request.BookAppointmentRequest;
import com.assignment.salonappointmentbookingsystem.request.CategoryRequest;
import com.assignment.salonappointmentbookingsystem.request.RegistrationRequest;
import com.assignment.salonappointmentbookingsystem.response.BookAppointmentResponse;
import com.assignment.salonappointmentbookingsystem.response.CategoryResponse;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface CommonMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    UserResponse userToUserResponse(User user);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User registrationRequestToUser(RegistrationRequest registrationRequest);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    CategoryResponse categoryToCategoryResponse(Category category);

    Category categoryRequestToCategory(CategoryRequest categoryRequest);

    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "objectIdToString")
    @Mapping(source = "categoryId", target = "categoryId", qualifiedByName = "objectIdToString")
    BookAppointmentResponse appointmentToAppointmentResponse(Appointment category);

    @Mapping(source = "userId", target = "userId", qualifiedByName = "stringToObjectId")
    @Mapping(source = "categoryId", target = "categoryId", qualifiedByName = "stringToObjectId")
    Appointment appointmentRequestToAppointment(BookAppointmentRequest bookAppointmentRequest);

    @Named("objectIdToString")
    default String objectIdToString(ObjectId id) {
        return String.valueOf(id);
    }

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return new ObjectId(id);
    }
}
