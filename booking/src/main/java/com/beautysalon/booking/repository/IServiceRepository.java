package com.beautysalon.booking.repository;

import com.beautysalon.booking.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IServiceRepository extends JpaRepository<Service, UUID> {}