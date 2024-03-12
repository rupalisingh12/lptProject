package com.leanplatform.MentorshipPlatform.mappers.BookingFunctnalityMapper;

import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingDTO;
import com.leanplatform.MentorshipPlatform.dto.BookingController.UserDTO;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Attendee;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.Booking;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class BookingMapper {
    public static CreateBookingDTO convertEntityToDto(Booking booking, UserEntity userEntity, List<Attendee> attendee){
        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        createBookingDTO.setUserId(userEntity.getUserId());
        createBookingDTO.setDescription(booking.getDescription());
        createBookingDTO.setEventTypeId(booking.getEventTypeId());
        createBookingDTO.setTitle(booking.getTitle());
        createBookingDTO.setId(booking.getBookingId());
        UserDTO userDTO=new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        createBookingDTO.setUser(userDTO);
        createBookingDTO.setAttendees(attendee);
        createBookingDTO.setStartTime(booking.getStartTime());
        createBookingDTO.setEndTime(booking.getEndTime());
        createBookingDTO.setStatus(booking.getStatus());
       // createBookingDTO.setAttendees(booking.getAttendees());
        //createBookingDTO.setLocation(booking.getLocation());
        return createBookingDTO;
    }
    public static List<CreateBookingDTO> convertEntityToDto1(List<Booking>bookingList){
        List<CreateBookingDTO> createBookingDTO=new ArrayList<>();
        CreateBookingDTO createBookingDTO1=new CreateBookingDTO();
        for(int i=0;i<bookingList.size();i++){
            Booking bookingElement=bookingList.get(i);
            createBookingDTO1.setDescription(bookingElement.getDescription());
            createBookingDTO1.setId(bookingElement.getBookingId());
            createBookingDTO1.setUserId(bookingElement.getUserId());
           ;
            createBookingDTO1.setTitle(bookingElement.getTitle());
            createBookingDTO1.setStartTime(bookingElement.getStartTime());
            createBookingDTO1.setEndTime(bookingElement.getEndTime());
            createBookingDTO1.setEventTypeId(bookingElement.getEventTypeId());

//            createBookingDTO1.setAttendees(bookingElement.getAttendees());
//            createBookingDTO1.setUser(bookingElement.getUser());
            createBookingDTO1.setId(bookingElement.getBookingId());
            createBookingDTO1.setUserId(bookingElement.getUserId());
            createBookingDTO1.setDescription(bookingElement.getDescription());
            createBookingDTO1.setTitle(bookingElement.getTitle());
            createBookingDTO1.setStartTime(bookingElement.getStartTime());
            createBookingDTO1.setEndTime(bookingElement.getEndTime());
            createBookingDTO1.setEventTypeId(bookingElement.getEventTypeId());
//            createBookingDTO1.setAttendees(bookingElement.getAttendees());
//            createBookingDTO1.setUser(bookingElement.getUser());
            createBookingDTO.add(createBookingDTO1);
        }
        return createBookingDTO;




    }
//    public static CreateBookingDTO convertEntityToDtoGetBooking(Booking booking){
//        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
//        createBookingDTO.setUserId(userEntity.getUserId());
//        createBookingDTO.setDescription(booking.getDescription());
//        createBookingDTO.setEventTypeId(booking.getEventTypeId());
//        createBookingDTO.setTitle(booking.getTitle());
//        createBookingDTO.setAttendee(attendee);
//        createBookingDTO.setStartTime(booking.getStartTime());
//        createBookingDTO.setEndTime(booking.getEndTime());
//        createBookingDTO.setStatus(booking.getStatus());
//        // createBookingDTO.setAttendees(booking.getAttendees());
//        //createBookingDTO.setLocation(booking.getLocation());
//        return createBookingDTO;
//    }

}
