package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.BookingController.GetBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorController.MentorAddedServiceResponse;
import com.leanplatform.MentorshipPlatform.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/addBooking")
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestParam(name = "userId") UUID userId, @RequestBody BookingRequest bookingRequest) {
        if (bookingRequest == null ||
                bookingRequest.getEventTypeId() == null ||bookingRequest.getStart()==null ||
                bookingRequest.getDescription()==null || bookingRequest.getResponse()==null) {
            return new ResponseEntity<>
                    (new CreateBookingResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return bookingService.createAbooking(bookingRequest, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateBookingResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/getAllBookings")
//    public ResponseEntity<GetBookingResponse> getAllBooking(@RequestParam(name = "userId") UUID userId) {
//        try {
//            return bookingService.getBookings(userId);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new GetBookingResponse
//                    (
//                            "0",
//                            "Caught in catch block",
//                            null
//                    ), HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    @GetMapping("/bookings/{bookingId}")
//    public ResponseEntity<GetBookingResponse> getBookingById(@PathVariable UUID bookingId, @RequestParam(name = "userId") UUID userId) {
//        try {
//            return bookingService.getBooking(bookingId, userId);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new GetBookingResponse("0", "Caught in catch block", null), HttpStatus.BAD_REQUEST);
//        }
//
//
//    }
//    @DeleteMapping("bookings/{bookingId}")
//    public ResponseEntity<CreateBookingResponse>deleteBookings(@PathVariable UUID bookingId, @RequestParam(name = "userId") UUID userId){
//        try {
//            return bookingService.deleteBooking(bookingId, userId);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new CreateBookingResponse("0", "Caught in catch block", null), HttpStatus.BAD_REQUEST);
//        }
//    }
}

