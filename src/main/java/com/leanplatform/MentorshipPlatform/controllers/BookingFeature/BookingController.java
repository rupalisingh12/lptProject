package com.leanplatform.MentorshipPlatform.controllers.BookingFeature;

import com.leanplatform.MentorshipPlatform.CalendarQuickstart;
import com.leanplatform.MentorshipPlatform.dto.BookingController.*;
import com.leanplatform.MentorshipPlatform.services.BookingFeatureService.BookingService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/Booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    CalendarQuickstart calendarQuickstart;

    @PostMapping("/addBooking")
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestParam(name = "userId") UUID userId, @RequestBody BookingRequest bookingRequest) {
        if (bookingRequest == null ||
                bookingRequest.getEventTypeId() == null || bookingRequest.getStart() == null ||
                bookingRequest.getDescription() == null || bookingRequest.getResponse() == null) {
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

    @GetMapping("/getAllBookings")
    public ResponseEntity<GetBookingResponse> getAllBooking(@RequestParam(name = "userId") UUID userId) {
        if (userId == null) {
            return new ResponseEntity<>(new GetBookingResponse
                    (
                            "0",
                            "Invalid request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return bookingService.getBookings(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new GetBookingResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<CreateBookingResponse> getBookingById(@PathVariable UUID bookingId, @RequestParam(name = "userId") UUID userId) {
        if (bookingId == null || userId == null) {
            return new ResponseEntity<>(new
                    CreateBookingResponse("0",
                    "Invalid request", null), HttpStatus.BAD_REQUEST);

        }
        try {
            return bookingService.getBooking(bookingId, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateBookingResponse("0", "Caught in catch block", null), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/updateBooking/{bookingId}")
    public ResponseEntity<CreateBookingResponse> updateBooking(@PathVariable UUID bookingId, @RequestParam(name = "userId") UUID userId, @RequestBody UpdateBookingRequest updateBookingRequest) {
        if (bookingId == null || userId == null || updateBookingRequest == null) {
            return new ResponseEntity<>(new
                    CreateBookingResponse("0",
                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }


        try {
            return bookingService.updateBooking(bookingId, userId, updateBookingRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateBookingResponse("0", "Caught in catch block", null), HttpStatus.BAD_REQUEST);
        }
    }


    // we are not using this api to delete the booking, we use update booking to update the status of the booking
    @DeleteMapping("bookings/{bookingId}")
    public ResponseEntity<DeleteBookingRespone> deleteBookings(@PathVariable UUID bookingId, @RequestParam(name = "userId") UUID userId) {
        if (bookingId == null || userId == null) {
            return new ResponseEntity<>(new
                    DeleteBookingRespone("0", "Invalid Request"), HttpStatus.BAD_REQUEST);
        }
        try {
            return bookingService.deleteBooking(bookingId, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new DeleteBookingRespone("0", "Caught in catch block"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMenteeWhoBookedSameSlot")
    public ResponseEntity<GetMenteeWhoBookedSameSlotResponse> getMenteeSameSlot(@RequestParam(name = "eventTypeId") UUID eventTypeId, @RequestParam(name = "startTime") LocalDateTime startTime, @RequestParam(name = "endTime") LocalDateTime endTime) {
        if (eventTypeId == null || startTime == null || endTime == null) {
            return new ResponseEntity<>(new
                    GetMenteeWhoBookedSameSlotResponse("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return bookingService.getMentee(eventTypeId, startTime, endTime);
        } catch (Exception e) {
            return new ResponseEntity<>(new GetMenteeWhoBookedSameSlotResponse("0", "Caught in catch block", null), HttpStatus.BAD_REQUEST);

        }

    }


//        @GetMapping("/getCode")
//    public  void  getUrl(HttpServletResponse response) throws GeneralSecurityException, IOException {
//        return calendarQuickstart.getCredentials();
//        String url=calendarQuickstart.getCredentials();
//        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", url);
//        response.sendRedirect(url);
//
//    }
    @GetMapping("/CreateMeetLink")
    public ResponseEntity<GetMenteeWhoBookedSameSlotResponse> CreatMeetingLink(@RequestParam(name = "userId") UUID userId) {

        try {
            return bookingService.CreateMeetingLink(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new GetMenteeWhoBookedSameSlotResponse("0", "Caught in catch block"+e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

        }




    }
    @PostMapping("/getAccessToken")
    public ResponseEntity<getAccessToken>GetAccessToken(getACcessTokenPayLoad getACcessTokenPayLoad){

        try{
          return bookingService.getAccessTokenAndRefreshToken( getACcessTokenPayLoad);
        }
        catch(Exception e){

            return new ResponseEntity<>(new getAccessToken("0", "Caught in catch block"+e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);


        }
    }
    @GetMapping("/CallBack")
    public ResponseEntity<AccessTokenResponse>GetAccessToken(@RequestParam(name = "code")String code  ){

        try{
            return bookingService.getCodeAndCOnvertItToAccesToken(code);
           // return bookingService.getAccessTokenAndRefreshToken(getACcessTokenPayLoad getACcessTokenPayLoad);
        }
        catch(Exception e){

            return new ResponseEntity<>(new AccessTokenResponse(null, 0,null,null,null), HttpStatus.BAD_REQUEST);


        }
    }

}

