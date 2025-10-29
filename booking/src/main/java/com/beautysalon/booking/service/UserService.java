package com.beautysalon.booking.service;

import com.beautysalon.booking.entity.User;
import com.beautysalon.booking.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --- Додаткова функціональність для UI ---
    
    /**
     * Повертає список усіх користувачів з бази даних.
     * Цей метод вирішує проблему компіляції.
     */
    public List<User> findAllUsers() {
        // Метод findAll() успадкований від JpaRepository
        return userRepository.findAll();
    }

    // --- Основні сценарії ---
    
    // Сценарій: Зареєструватися
    public User registerClient(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Користувач з таким email вже існує.");
        }
        user.setRole("CLIENT"); 
        return userRepository.save(user);
    }

    // Сценарій: Авторизуватися
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; 
    }
}