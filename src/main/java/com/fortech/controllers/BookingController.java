package com.fortech.controllers;

import com.fortech.entity.Booking;
import com.fortech.exceptions.UserNotFoundException;
import com.fortech.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("myapi")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

        @PostMapping("/createBooking")
    public Booking createBooking(@RequestParam (name = "bookId") Integer bookId) throws UserNotFoundException {
        return bookingService.createBooking(bookId);
    }



    @DeleteMapping("/cancelBooking")
    public void cancelBooking(@RequestParam (name = "bookingId")Integer bookingId){
        bookingService.cancelBooking(bookingId);

    }
}
