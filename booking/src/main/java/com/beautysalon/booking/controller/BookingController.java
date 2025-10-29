package com.beautysalon.booking.controller;

import com.beautysalon.booking.entity.Booking;
import com.beautysalon.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam UUID clientId,
            @RequestParam UUID serviceId,
            @RequestParam UUID masterId,
            @RequestParam String dateTime) { 

        try {
            LocalDateTime desiredDateTime = LocalDateTime.parse(dateTime);
            Booking newBooking = bookingService.createBooking(
                    clientId, serviceId, masterId, desiredDateTime);
            
            return new ResponseEntity<>(newBooking, HttpStatus.CREATED); 
            
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Booking>> getClientBookings(@PathVariable UUID clientId) {
        List<Booking> bookings = bookingService.getBookingsByClient(clientId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Сценарій "Переглянути/скасувати бронювання" (Скасування)
    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable UUID bookingId) {
        try {
            Booking cancelledBooking = bookingService.cancelBooking(bookingId);
            return new ResponseEntity<>(cancelledBooking, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}