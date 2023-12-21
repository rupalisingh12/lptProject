package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingDTO;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.BookingController.GetBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.CreateEventDTO;
import com.leanplatform.MentorshipPlatform.entities.Attendee;
import com.leanplatform.MentorshipPlatform.entities.Booking;
import com.leanplatform.MentorshipPlatform.entities.Location;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;
import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.mappers.BookingMapper;
import com.leanplatform.MentorshipPlatform.repositories.*;
import com.leanplatform.MentorshipPlatform.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired BookingRepository bookingRepository;
    @Autowired UserRepository userRepository;
    @Autowired LocationRepository location;
    @Autowired AttendeeRepository attendeeRepository;
    @Autowired EventTypesRepository eventTypesRepository;
    @Override
    public ResponseEntity<CreateBookingResponse>createAbooking(BookingRequest bookingRequest, UUID userId) {
        if (bookingRequest == null ||
                bookingRequest.getEventTypeId() == null ||bookingRequest.getStart()==null ||
                bookingRequest.getDescription()==null || bookingRequest.getResponse()==null) {
            return new ResponseEntity<>(new CreateBookingResponse("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setDescription(bookingRequest.getDescription());
        booking.setEventTypeId(bookingRequest.getEventTypeId());
        //to put title name we
        UserEntity userEntity=userRepository.findByUserId(userId);
        String name=userEntity.getName();
        String name1=bookingRequest.getResponse().getName();
        booking.setTitle(name+ "between"+name1);
        //to put startTime
        booking.setStartTime(bookingRequest.getStart());
        //to put endTime
        Integer length1= eventTypesRepository.findEventTypeLengthByEventId(bookingRequest.getEventTypeId());
        LocalDateTime endTime=bookingRequest.getStart().plusMinutes(length1);
        booking.setEndTime(endTime);
        //put attendees
        Attendee attendee1=new Attendee();
        attendee1.setEmail(bookingRequest.getResponse().getEmail());
        attendee1.setName(bookingRequest.getResponse().getName());
        //put status
        booking.setStatus(BookingEnums.Approved);
       Booking booking1= bookingRepository.save(booking);
       attendee1.setBookingId(booking1.getBookingId());
       attendeeRepository.save(attendee1);
      Attendee attendee=attendeeRepository.findByBookingId(booking1.getBookingId());


        //UUID locationID=location.save(bookingRequest.getLoction());
      //  booking.setLocationId(locationID);
        UserEntity userE= userRepository.findByUserId(userId);
       CreateBookingDTO createBookingDTO1= BookingMapper.convertEntityToDto(booking,userE,attendee);
        return new ResponseEntity<>(new
                CreateBookingResponse("1",
                "Booking Created",createBookingDTO1 ), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<GetBookingResponse>getBookings(UUID userId){
        List<Booking> bookingList=bookingRepository.findAllByUserId(userId);
      List<CreateBookingDTO>createBookingDTO=  BookingMapper.convertEntityToDto1(bookingList);
        return new ResponseEntity<>(new
                GetBookingResponse("1",
                "Booking List is",createBookingDTO ), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<CreateBookingResponse>getBooking(UUID bookingId,UUID userId){
       Booking booking=bookingRepository.findByBookingIdAndUserId(userId,bookingId);
       if(booking!=null) {
           Attendee attendee = attendeeRepository.findByBookingId(booking.getBookingId());
           UserEntity userEntity = userRepository.findByUserId(userId);
           CreateBookingDTO createBookingDTO = BookingMapper.convertEntityToDto(booking, userEntity, attendee);

           return new ResponseEntity<>(new
                   CreateBookingResponse("1",
                   "Booking Created", createBookingDTO), HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(new
                   CreateBookingResponse("1",
                   "Booking Created",null), HttpStatus.OK);

       }

    }
//    @Override
//    public ResponseEntity<CreateBookingResponse>deleteBooking(UUID bookingId, UUID userId){
//        List<Booking> bookingList=bookingRepository.findByBookingIdAndUserId(userId);
//        bookingRepository.deleteByUserIdAndBookingId(bookingId,userId);
//        List<CreateBookingDTO>createBookingDTO=  BookingMapper.convertEntityToDto1(bookingList);
//
//
//        return new ResponseEntity<>(new
//                CreateBookingResponse("1",
//                "Booking Created",createBookingDTO.get(0) ), HttpStatus.OK);
//
//
//    }
//


    }






