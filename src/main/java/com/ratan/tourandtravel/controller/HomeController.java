package com.ratan.tourandtravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Home Page
    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }

    // About Page
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    // Booking Page
    @GetMapping("/booking")
    public String bookingPage() {
        return "booking";
    }

    // Contact Page
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
}
