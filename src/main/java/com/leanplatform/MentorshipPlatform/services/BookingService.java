package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.BookingController.GetBookingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BookingService {

    public ResponseEntity<CreateBookingResponse>createAbooking(BookingRequest bookingRequest, UUID userId);
//    public ResponseEntity<GetBookingResponse>getBookings(UUID userId);
//    public ResponseEntity<GetBookingResponse>getBooking(UUID bookingId,UUID userId);
//    public ResponseEntity<CreateBookingResponse>deleteBooking(UUID bookingId, UUID userId);
}
