package com.ratan.tourandtravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveBooking(String name, String phone, String email,
                            String pickup, String dropLocation, LocalDate travelDate) {

        String sql = "INSERT INTO booking_requests(name, phone, email, pickup, drop_location, travel_date) VALUES (?, ?, ?, ?, ?, ?)";

        // ✅ Step 1: Save booking (ALWAYS WORKS)
        jdbcTemplate.update(sql, name, phone, email, pickup, dropLocation, travelDate);

        // ✅ Step 2: Send email safely (IMPORTANT FIX)
        try {
            emailService.sendBookingConfirmation(email, name);

            emailService.sendBookingNotificationToOwner(
                    name, email, phone, pickup, dropLocation, travelDate
            );

        } catch (Exception e) {
            System.out.println("Email failed but booking saved: " + e.getMessage());
        }
    }
}