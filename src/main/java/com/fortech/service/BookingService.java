package com.fortech.service;

import com.fortech.entity.*;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    public List<Booking> findAll();

    public Booking findById (Integer bookingId);

    public void cancelBooking (Integer bookingId);

    public Booking createBooking(Integer bookId);
}
