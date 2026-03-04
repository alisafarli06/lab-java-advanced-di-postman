package com.ironhack.labpostman.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EarlyBirdDiscountService {

    public  String calculateDiscount(String eventDate, String bookingDate) {

        if (eventDate ==null || bookingDate == null) {
            return "Event date and booking date must be provided.";
        }

        LocalDate eventTime = LocalDate.parse(eventDate);
        LocalDate bookingTime = LocalDate.parse(bookingDate);

        long daysBetween = ChronoUnit.DAYS.between(bookingTime, eventTime);

        if (daysBetween >= 30) {
            return "You get a 15% discount for booking early!";
        }else if (daysBetween >= 0) {
            return "No discount available. Book at least 30 days in advance to get a discount.";
        } else {
            return "Invalid booking date. Booking date cannot be after the event date.";
        }

    }
}
