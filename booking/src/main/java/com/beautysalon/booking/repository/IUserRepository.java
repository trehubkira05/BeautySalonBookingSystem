package com.beautysalon.booking.repository;

import com.beautysalon.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}