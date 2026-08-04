package com.ratan.tourandtravel.controller;

import com.ratan.tourandtravel.service.BookingService;
import com.ratan.tourandtravel.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class FormController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ContactService contactService;

    @PostMapping("/booking")
    public String submitBooking(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String pickup,
            @RequestParam String dropLocation,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate travelDate,

            Model model) {
        // Check if travel date is in the past
        if (travelDate.isBefore(LocalDate.now())) {
            model.addAttribute("error", "Travel date must be today or in the future.");
            return "booking"; // return back to booking page with error
        }

        bookingService.saveBooking(name, phone, email, pickup, dropLocation, travelDate);
        model.addAttribute("success", "Booking request submitted successfully!");
        return "booking";
    }

    @PostMapping("/contact")
    public String submitContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String subject,
            @RequestParam String message,
            Model model) {

        try {
            contactService.saveContact(name, email, phone, subject, message);
            model.addAttribute("success", "Message sent successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to send message. Please try again.");
        }
        return "contact";
    }
}