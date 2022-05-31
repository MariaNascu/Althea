package com.fortech.service.impl;

import com.fortech.dtos.BookResponse;
import com.fortech.entity.Book;
import com.fortech.entity.Booking;
import com.fortech.entity.User;
import com.fortech.exceptions.UserNotFoundException;
import com.fortech.repository.BookRepository;
import com.fortech.repository.BookingRepository;
import com.fortech.repository.UserRepository;
import com.fortech.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

import static java.util.Calendar.FRIDAY;
import static org.springframework.data.util.Optionals.next;


@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find booking " + bookingId));
    }


    @Override
    public void cancelBooking(Integer bookingId) {

        bookingRepository.deleteById(bookingId);

    }


    @Override
    public Booking createBooking(Integer bookId) throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        Booking newBooking = new Booking();

        newBooking.setUserId(user.getUserId());
        newBooking.setBookId(bookId);

        LocalDate startDate = LocalDate.now();
        LocalDate expiryDate = startDate.plusWeeks(2);

        newBooking.setBookingStartDate(startDate);
        newBooking.setBookingExpirationDate(expiryDate);

        return bookingRepository.save(newBooking);
    }


}






