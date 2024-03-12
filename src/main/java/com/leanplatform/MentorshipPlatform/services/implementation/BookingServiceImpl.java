package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.BookingController.*;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.Booking;
import com.leanplatform.MentorshipPlatform.entities.EventTypeFeature.EventType;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Attendee;
import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.BookingFunctnalityMapper.BookingMapper;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.BookingRepository;
import com.leanplatform.MentorshipPlatform.repositories.EventTypeRepository.EventTypesRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.AttendeeRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.LocationRepository;
import com.leanplatform.MentorshipPlatform.services.BookingFeatureService.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository location;
    @Autowired
    AttendeeRepository attendeeRepository;
    @Autowired
    EventTypesRepository eventTypesRepository;


    @Transactional
    @Override
    public ResponseEntity<CreateBookingResponse>createAbooking(BookingRequest bookingRequest, UUID userId) {
        if (bookingRequest == null ||
                bookingRequest.getEventTypeId() == null || bookingRequest.getStart() == null ||
                bookingRequest.getDescription() == null ) {
            return new ResponseEntity<>
                    (new CreateBookingResponse("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }

        // CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        List<Booking> list = bookingRepository.findByUserIdAndDate(userId, bookingRequest.getStart().toLocalDate());
       EventType eventTypeFinal= eventTypesRepository.findByEventId(bookingRequest.getEventTypeId());
       if(eventTypeFinal==null){
           return new ResponseEntity<>
                   (new CreateBookingResponse("0", "This event Does not exist iin the db", null), HttpStatus.NOT_FOUND);

       }
        Integer length1 = eventTypesRepository.findEventTypeLengthByEventId(bookingRequest.getEventTypeId());
        EventType eventType=eventTypesRepository.findByEventId(bookingRequest.getEventTypeId());
        LocalDateTime endTime = bookingRequest.getStart().plusMinutes(length1);
        LocalTime startTime1 = bookingRequest.getStart().toLocalTime();
        LocalTime endTime1 = endTime.toLocalTime();
        Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1, endTime1);
        if (list != null) {
            //if list is not empty
            for (int i = 0; i < list.size(); i++) {
                Booking ans = list.get(i);
                Set<Long> s = ans.getSlotIds();
                Set<Long> commonElements = new HashSet<>(listOfSlots);

                // Retain only the common elements in the copy
                commonElements.retainAll(s);
                if (!commonElements.isEmpty()) {
                    return new ResponseEntity<>(new CreateBookingResponse
                            (
                                    "0",
                                    "Booking already exists for this mentor and slotId.",
                                    null
                            ), HttpStatus.BAD_REQUEST);

                } else {
                    //continue
                }
            }

        } else {
            //if list is empty ,no prob, a new booking can be added
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setDescription(bookingRequest.getDescription());
            booking.setEventTypeId(bookingRequest.getEventTypeId());
            //to put title name we
            UserEntity userEntity = userRepository.findByUserId(userId);
            String name = userEntity.getName();
            String name1 = bookingRequest.getResponse().getName();
            booking.setTitle(eventType.getTitle()+" between " + name + " and " + name1);
            //to put startTime
            booking.setStartTime(bookingRequest.getStart());
            //put date
            LocalDate date = bookingRequest.getStart().toLocalDate();
            booking.setDate(date);
            booking.setSlotIds(listOfSlots);
            //put endTime
            LocalDateTime endTime11=bookingRequest.getStart().plusMinutes(length1);
            booking.setEndTime(endTime11);
            //put attendees
            List<Attendee>attendeesList=new ArrayList<>();
            Attendee attendee1 = new Attendee();
            attendee1.setEmail(bookingRequest.getResponse().getEmail());
            attendee1.setName(bookingRequest.getResponse().getName());
            attendeesList.add(attendee1);
            //put status
            booking.setStatus(BookingEnums.ACCEPTED);
            Booking booking1 = bookingRepository.save(booking);
            attendee1.setBookingId(booking1.getBookingId());
            attendeeRepository.save(attendee1);
            List<Attendee> attendee = attendeeRepository.findByBookingId(booking1.getBookingId());
            UserEntity userE = userRepository.findByUserId(userId);
            CreateBookingDTO createBookingDTO1 = BookingMapper.convertEntityToDto(booking, userE, attendeesList);
            return new ResponseEntity<>(new
                    CreateBookingResponse("1",
                    "Booking Created", createBookingDTO1), HttpStatus.OK);

        }


//        Booking booking = new Booking();
//        booking.setUserId(userId);
//        booking.setDescription(bookingRequest.getDescription());
//        booking.setEventTypeId(bookingRequest.getEventTypeId());
//        //to put title name we
//        UserEntity userEntity=userRepository.findByUserId(userId);
//        String name=userEntity.getName();
//        String name1=bookingRequest.getResponse().getName();
//        booking.setTitle(name+ " between "+name1);
//        //to put startTime
//        booking.setStartTime(bookingRequest.getStart());
//        //put date
//        LocalDate date=bookingRequest.getStart().toLocalDate();
//        booking.setDate(date);
        //to put endTime
//        Integer length1= eventTypesRepository.findEventTypeLengthByEventId(bookingRequest.getEventTypeId());
//        LocalDateTime endTime=bookingRequest.getStart().plusMinutes(length1);
//        booking.setEndTime(endTime);
//        //to convert into slotIds
//        LocalTime startTime1 = bookingRequest.getStart().toLocalTime();
//        LocalTime endTime1 = endTime.toLocalTime();
//
//        Set<Long> listOfSlots= AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTime1,endTime1);
//        //slotIds
////        List<Booking>list=bookingRepository.findByUserIdAndDate(userId,bookingRequest.getStart().toLocalDate());
//        if(list==null) {
//
//
//            booking.setSlotIds(listOfSlots);
//
//
//            //put attendees
//            Attendee attendee1 = new Attendee();
//            attendee1.setEmail(bookingRequest.getResponse().getEmail());
//            attendee1.setName(bookingRequest.getResponse().getName());
//            //put status
//            booking.setStatus(BookingEnums.Approved);
//            Booking booking1 = bookingRepository.save(booking);
//            attendee1.setBookingId(booking1.getBookingId());
//            attendeeRepository.save(attendee1);
//            Attendee attendee = attendeeRepository.findByBookingId(booking1.getBookingId());
//
//
//            //UUID locationID=location.save(bookingRequest.getLoction());
//            //  booking.setLocationId(locationID);
//            UserEntity userE = userRepository.findByUserId(userId);
//            CreateBookingDTO createBookingDTO1 = BookingMapper.convertEntityToDto(booking, userE, attendee);
//            return new ResponseEntity<>(new
//                    CreateBookingResponse("1",
//                    "Booking Created", createBookingDTO1), HttpStatus.OK);
//        }
//        else {
//            for (int i = 0; i < list.size(); i++) {
//                Booking ans = list.get(i);
//                Set<Long> s = ans.getSlotIds();
//                Set<Long> commonElements = new HashSet<>(listOfSlots);
//
//                // Retain only the common elements in the copy
//                commonElements.retainAll(s);
//                if (!commonElements.isEmpty()) {
//                    return new ResponseEntity<>(new CreateBookingResponse
//                            (
//                                    "0",
//                                    "Booking already exists for this mentor and slotId.",
//                                    null
//                            ), HttpStatus.BAD_REQUEST);
//
//                }
//            }
//            booking.setSlotIds(listOfSlots);


        //put attendees
//            Attendee attendee1 = new Attendee();
//            attendee1.setEmail(bookingRequest.getResponse().getEmail());
//            attendee1.setName(bookingRequest.getResponse().getName());
//            //put status
//            booking.setStatus(BookingEnums.Approved);
//            Booking booking1 = bookingRepository.save(booking);
//            attendee1.setBookingId(booking1.getBookingId());
//            attendeeRepository.save(attendee1);
//            Attendee attendee = attendeeRepository.findByBookingId(booking1.getBookingId());
//
//
//            //UUID locationID=location.save(bookingRequest.getLoction());
//            //  booking.setLocationId(locationID);
//            UserEntity userE = userRepository.findByUserId(userId);
//            CreateBookingDTO createBookingDTO1 = BookingMapper.convertEntityToDto(booking, userE, attendee);
//            return new ResponseEntity<>(new
//                    CreateBookingResponse("1",
//                    "Booking Created", createBookingDTO1), HttpStatus.OK);
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setDescription(bookingRequest.getDescription());
        booking.setEventTypeId(bookingRequest.getEventTypeId());
        //to put title name we
        UserEntity userEntity = userRepository.findByUserId(userId);
        String name = userEntity.getName();
        String name1 = bookingRequest.getResponse().getName();
        booking.setTitle(eventType.getTitle()+" between " + name + " and " + name1);
        //to put startTime
        booking.setStartTime(bookingRequest.getStart());
        //put date
        LocalDate date = bookingRequest.getStart().toLocalDate();
        booking.setDate(date);
        //put endTime
        LocalDateTime endTime11=bookingRequest.getStart().plusMinutes(length1);
        booking.setEndTime(endTime11);
        booking.setSlotIds(listOfSlots);
        //put attendees
        List<Attendee>attendeesList=new ArrayList<>();
        Attendee attendee1 = new Attendee();
        attendee1.setEmail(bookingRequest.getResponse().getEmail());
        attendee1.setName(bookingRequest.getResponse().getName());
        attendeesList.add(attendee1);

        //put status
        booking.setStatus(BookingEnums.ACCEPTED);
        Booking booking1 = bookingRepository.save(booking);
        attendee1.setBookingId(booking1.getBookingId());
        attendeeRepository.save(attendee1);
        List<Attendee> attendee = attendeeRepository.findByBookingId(booking1.getBookingId());
        UserEntity userE = userRepository.findByUserId(userId);
        CreateBookingDTO createBookingDTO1 = BookingMapper.convertEntityToDto(booking, userE,attendeesList );
        return new ResponseEntity<>(new
                CreateBookingResponse("1",
                "Booking Created", createBookingDTO1), HttpStatus.OK);
    }






    @Override
    public ResponseEntity<GetBookingResponse>getBookings(UUID userId){
        if(userId==null){
            return new ResponseEntity<>(new GetBookingResponse
                    (
                            "0",
                            "Invalid request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        List<Booking> bookingList=bookingRepository.findAllByUserId(userId);
        if(!bookingList.isEmpty()) {
            List<CreateBookingDTO> createBookingDTOList = new ArrayList<>();

            for (int i = 0; i < bookingList.size(); i++) {

                Booking booking1 = bookingList.get(i);
                List<Attendee> attendee = attendeeRepository.findByBookingId(booking1.getBookingId());
                UserEntity userE = userRepository.findByUserId(userId);

                CreateBookingDTO createBookingDTO = BookingMapper.convertEntityToDto(booking1, userE, attendee);
                createBookingDTOList.add(createBookingDTO);
            }
            return new ResponseEntity<>(new
                    GetBookingResponse("1",
                    "Booking List are", createBookingDTOList), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new
                    GetBookingResponse("1",
                    "There is no booking for the required userId", null), HttpStatus.OK);

        }
    }
    @Override
    public ResponseEntity<CreateBookingResponse>getBooking(UUID bookingId,UUID userId){
        if(bookingId==null || userId==null){
            return new ResponseEntity<>(new
                    CreateBookingResponse("0",
                    "Invalid request", null), HttpStatus.BAD_REQUEST);

        }
       Booking booking=bookingRepository.findByBookingIdAndUserId(bookingId,userId);
       if(booking!=null) {
           List<Attendee> attendee = attendeeRepository.findByBookingId(booking.getBookingId());
           UserEntity userEntity = userRepository.findByUserId(userId);
           CreateBookingDTO createBookingDTO = BookingMapper.convertEntityToDto(booking, userEntity, attendee);

           return new ResponseEntity<>(new
                   CreateBookingResponse("1",
                   "Booking List is", createBookingDTO), HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(new
                   CreateBookingResponse("0",
                   "No Booking with this userId exist",null), HttpStatus.OK);

       }

    }
    @Override
    public ResponseEntity<CreateBookingResponse>updateBooking(UUID bookingId,UUID userId,UpdateBookingRequest updateBookingRequest){
        if(bookingId==null || userId==null || updateBookingRequest==null){
            return new ResponseEntity<>(new
                    CreateBookingResponse("0",
                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        Booking booking=bookingRepository.findByBookingIdAndUserId(bookingId,userId);
        if(booking!=null) {
            booking.setStatus(BookingEnums.valueOf(updateBookingRequest.getStatus().toUpperCase()));
            bookingRepository.save(booking);
            List<Attendee> attendee = attendeeRepository.findByBookingId(booking.getBookingId());
            UserEntity userE = userRepository.findByUserId(userId);
            CreateBookingDTO createBookingDTO1 = BookingMapper.convertEntityToDto(booking, userE, attendee);
            return new ResponseEntity<>(new
                    CreateBookingResponse("1",
                    "Booking Updated", createBookingDTO1), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new
                    CreateBookingResponse("0",
                    "No booking with this userId exist", null), HttpStatus.NOT_FOUND);

        }

       // BookingEnums.valueOf(updateBookingRequest.getStatus().toUpperCase());


    }
    @Override
    public ResponseEntity<DeleteBookingRespone>deleteBooking(UUID bookingId, UUID userId){
        if(bookingId==null || userId==null){
            return new ResponseEntity<>(new
                    DeleteBookingRespone("0", "Invalid Request"), HttpStatus.BAD_REQUEST);
        }
        Booking booking=bookingRepository.findByBookingIdAndUserId(bookingId,userId);
        if(booking!=null) {
            bookingRepository.delete(booking);
            //   List<CreateBookingDTO>createBookingDTO=  BookingMapper.convertEntityToDtoDelete(booking,userId);


            return new ResponseEntity<>(new
                    DeleteBookingRespone("1", "Booking with id  : " +  bookingId + "  deleted successfully"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new
                    DeleteBookingRespone("0", "BookingId does not exist"), HttpStatus.NOT_FOUND);

        }


    }




    }






