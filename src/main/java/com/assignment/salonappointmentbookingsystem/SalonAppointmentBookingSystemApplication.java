package com.assignment.salonappointmentbookingsystem;

import com.assignment.salonappointmentbookingsystem.model.Role;
import com.assignment.salonappointmentbookingsystem.model.User;
import com.assignment.salonappointmentbookingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class SalonAppointmentBookingSystemApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SalonAppointmentBookingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!userRepository.existsByRole(Role.ROLE_ADMIN)) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setPassword(passwordEncoder.encode("admin@123#"));
			user.setFullName("Admin");
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
		}
	}
}
