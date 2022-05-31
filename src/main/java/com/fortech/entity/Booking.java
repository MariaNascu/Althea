package com.fortech.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "user_id")
    private Integer userId;


    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "booking_startdate")
    private LocalDate bookingStartDate;


    @Column(name = "booking_expirationdate")
    private LocalDate bookingExpirationDate;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(LocalDate bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public LocalDate getBookingExpirationDate() {
        return bookingExpirationDate;
    }

    public void setBookingExpirationDate(LocalDate bookingExpirationDate) {
        this.bookingExpirationDate = bookingExpirationDate;
    }
}
