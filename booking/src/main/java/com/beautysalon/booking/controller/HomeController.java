package com.beautysalon.booking.controller;

import com.beautysalon.booking.entity.User;
import com.beautysalon.booking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обробляє кореневий шлях "/" і виводить дані з таблиці users.
     */
    @GetMapping("/")
    public String showData(Model model) {
        try {
            // Цей виклик тепер піде до MySQL через репозиторій
            List<User> users = userService.findAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("statusMessage", "Успішне підключення до MySQL Server.");
        } catch (Exception e) {
            // Обробляємо помилки підключення, якщо вони виникнуть
            model.addAttribute("users", List.of()); 
            model.addAttribute("statusMessage", "Помилка підключення до БД: " + e.getMessage());
        }
        return "home_data"; // Повертаємо шаблон home_data.html
    }
}