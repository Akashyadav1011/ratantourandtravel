package com.ratan.tourandtravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendContactNotificationToOwner(String name, String email,
                                               String phone, String subject, String messageText) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("ratantourandtravel@gmail.com");
        mail.setSubject("New Contact Form Submission: " + subject);

        mail.setText(
                "New Contact Message Received:\n\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone + "\n\n" +
                        "Message:\n" + messageText
        );

        mailSender.send(mail);
    }

    public void sendContactAutoReply(String toEmail, String customerName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Thank You for Contacting Ratan Tour & Travel");

        message.setText(
                "Dear " + customerName + ",\n\n" +
                        "Thank you for contacting us.\n" +
                        "Our team will get back to you shortly.\n\n" +
                        "Regards,\nRatan Tour & Travel"
        );

        mailSender.send(message);
    }

    public void sendBookingNotificationToOwner(String name, String email,
                                               String phone, String pickup,
                                               String dropLocation,
                                               LocalDate travelDate) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("ratantourandtravel@gmail.com"); // Owner email
        message.setSubject("New Booking Received - Ratan Tour & Travel");

        message.setText(
                "New Booking Details:\n\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone + "\n" +
                        "Pickup Location: " + pickup + "\n" +
                        "Drop Location: " + dropLocation + "\n" +
                        "Travel Date: " + travelDate + "\n"
        );

        mailSender.send(message);
    }

    public void sendBookingConfirmation(String toEmail, String customerName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Booking Confirmation - Ratan Tour & Travel");

        message.setText(
                "Dear " + customerName + ",\n\n" +
                        "Your booking has been successfully received.\n" +
                        "Our team will contact you shortly.\n\n" +
                        "Thank you for choosing Ratan Tour & Travel.\n\n" +
                        "Regards,\nRatan Tour & Travel"
        );

        mailSender.send(message);
    }
}
