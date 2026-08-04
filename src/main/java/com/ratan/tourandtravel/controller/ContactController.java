package com.ratan.tourandtravel.controller;

import com.ratan.tourandtravel.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact-success")
    public String contactSuccess() {
        return "contact-success";
    }

    @PostMapping("/contact-form")
    public String sendMessage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String subject,
            @RequestParam String message,
            RedirectAttributes redirectAttributes
    ) {

        contactService.saveMessage(name, email, phone, subject, message);

        String whatsappMessage =
                "Hello Ratan Tour & Travel,\n\n" +
                        "Name: " + name + "\n" +
                        "Phone: " + phone + "\n" +
                        "Subject: " + subject + "\n\n" +
                        "Please contact me.";

        String whatsappUrl =
                "https://wa.me/919415787325?text=" +
                        URLEncoder.encode(whatsappMessage, StandardCharsets.UTF_8);

        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("whatsappUrl", whatsappUrl);

        return "redirect:/contact-success";
    }
}