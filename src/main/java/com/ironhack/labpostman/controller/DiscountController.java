package com.ironhack.labpostman.controller;

import com.ironhack.labpostman.service.EarlyBirdDiscountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DiscountController {

    private final Optional<EarlyBirdDiscountService> earlyBirdDiscountService;

    public DiscountController(Optional<EarlyBirdDiscountService> earlyBirdDiscountService) {
        this.earlyBirdDiscountService = earlyBirdDiscountService;
    }

    @GetMapping("/api/discount")
    public String getDiscount(@RequestParam String eventDate,@RequestParam String bookingDate) {
        return earlyBirdDiscountService.get().calculateDiscount(eventDate, bookingDate);
    }


}
