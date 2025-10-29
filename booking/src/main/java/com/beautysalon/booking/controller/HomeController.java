package com.beautysalon.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController поєднує @Controller та @ResponseBody, повертаючи дані (JSON/текст)
@RestController
public class HomeController {

    // Обробляє GET-запити на кореневий шлях "/"
    @GetMapping("/")
    public String welcome() {
        return "Welcome to Beauty Salon Booking System API! Architecture is running successfully.";
    }
    
    // Додатковий метод для перевірки статусу
    @GetMapping("/status")
    public String status() {
        return "System Status: UP";
    }
}