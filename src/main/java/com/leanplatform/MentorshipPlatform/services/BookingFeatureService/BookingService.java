package com.leanplatform.MentorshipPlatform.services.BookingFeatureService;

import com.leanplatform.MentorshipPlatform.dto.BookingController.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BookingService {

    public ResponseEntity<CreateBookingResponse>createAbooking(BookingRequest bookingRequest, UUID userId);
   public ResponseEntity<GetBookingResponse>getBookings(UUID userId);
    public ResponseEntity<CreateBookingResponse>getBooking(UUID bookingId,UUID userId);
    public ResponseEntity<CreateBookingResponse>updateBooking(UUID bookingId,UUID userId,UpdateBookingRequest updateBookingRequest);
   public ResponseEntity<DeleteBookingRespone>deleteBooking(UUID bookingId, UUID userId);
}
