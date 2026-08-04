package com.ratan.tourandtravel.controller;

import com.ratan.tourandtravel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Show booking page
    @GetMapping("/booking-success")
    public String showBookingPage() {

        return "booking-success"; // booking.html
    }

    @PostMapping("/booking-success")
    public String bookingSuccess(@RequestParam String name,
                                 @RequestParam String destination) {

        String phone = "919415787325";

        String message = "Hello " + name +
                ", your booking for " + destination +
                " is confirmed. Team Ratan Tour & Travel.";

        String url = "https://wa.me/" + phone + "?text=" +
                URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "redirect:" + url;
    }
    // Handle form submission
    @PostMapping("/saveBooking")
    public String saveBooking(@RequestParam String name,
                              @RequestParam String phone,
                              @RequestParam String email,
                              @RequestParam String pickup,
                              @RequestParam String dropLocation,
                              @RequestParam String travelDate,
                              RedirectAttributes redirectAttributes) {

        bookingService.saveBooking(
                name,
                phone,
                email,
                pickup,
                dropLocation,
                LocalDate.parse(travelDate)
        );

        // Pass data to success page
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("pickup", pickup);
        redirectAttributes.addFlashAttribute("dropLocation", dropLocation);
        redirectAttributes.addFlashAttribute("travelDate", travelDate);

        return "redirect:/booking-success";
    }

}