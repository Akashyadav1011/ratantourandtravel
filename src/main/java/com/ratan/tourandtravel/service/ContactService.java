package com.ratan.tourandtravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailService emailService;

    public void saveContact(String name, String email, String phone,
                            String subject, String message) {

        String sql = "INSERT INTO contact_messages(name, email, phone, subject, message) VALUES (?, ?, ?, ?, ?)";

        // ✅ Step 1: Save to DB
        jdbcTemplate.update(sql, name, email, phone, subject, message);

        // ✅ Step 2: Send email safely (IMPORTANT FIX)
        try {
            // Auto reply to user
            emailService.sendContactAutoReply(email, name);

            // Notify owner
            emailService.sendContactNotificationToOwner(
                    name, email, phone, subject, message
            );

        } catch (Exception e) {
            System.out.println("Email failed but message saved: " + e.getMessage());
        }
    }

    public void saveMessage(String name, String email, String phone, String subject, String message) {
    }
}