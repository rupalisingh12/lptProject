package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.BookingController.*;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.Booking;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.BookingSlotCountTable;
import com.leanplatform.MentorshipPlatform.entities.EventTypeFeature.EventType;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Attendee;
import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.BookingFunctnalityMapper.BookingMapper;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.BookingRepository;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.BookingSlotCountTableRepository;
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
    BookingSlotCountTableRepository bookingSlotCountTableRepository;
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
        List<BookingSlotCountTable> bookingSlotCountTableList= bookingSlotCountTableRepository.findByDateAndEventTypeId(bookingRequest.getStart().toLocalDate(),bookingRequest.getEventTypeId());
      //  Integer length1 = eventTypesRepository.findEventTypeLengthByEventId(bookingRequest.getEventTypeId());
        EventType eventType=eventTypesRepository.findByEventId(bookingRequest.getEventTypeId());
        LocalDateTime endTime = bookingRequest.getStart().plusMinutes(eventType.getLength());
        LocalTime startTime1 = bookingRequest.getStart().toLocalTime();
        LocalTime endTime1 = endTime.toLocalTime();
        Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1, endTime1);
//        ArrayList<Long>ans=new ArrayList<>();
//        for(int h=0;h<bookingSlotCountTableList.size();h++){
//            BookingSlotCountTable bookingSlotCountTable=bookingSlotCountTableList.get(h);
//           Long slotId= bookingSlotCountTable.getSlotId();
//           ans.add(slotId);
//        }
//        List<Long> result = new ArrayList<>(listOfSlots);
//        result.removeAll(ans);
//        for(int i=0;i<result.size();i++){
//           // result.get(i);
//            BookingSlotCountTable bookingSlotCountTable = new BookingSlotCountTable();
//            bookingSlotCountTable.setDate(bookingRequest.getStart().toLocalDate());
//            bookingSlotCountTable.setEventTypeId(bookingRequest.getEventTypeId());
//            bookingSlotCountTable.setSlotId(result.get(i));
//            bookingSlotCountTable.setCount((long)0);
//
//        }

        for(int i=0;i<bookingSlotCountTableList.size();i++) {
            BookingSlotCountTable bookingSlotCountTable = bookingSlotCountTableList.get(i);
            Long slot = bookingSlotCountTable.getSlotId();
            if (listOfSlots.contains(slot)) {
                Long slotCount = bookingSlotCountTable.getCount();
                if (eventType.getNoOfStudents() >slotCount) {
                    //let the booking Happen
                } else {
                    //booking can no happen
                    return new ResponseEntity<>(new CreateBookingResponse
                            (
                                    "0",
                                    "Booking exeeded the totalNoStudents allowed",
                                    null
                            ), HttpStatus.BAD_REQUEST);

                }
            }
        }
        //if it comes out of the above for loop that means , the booking can be done
        // now in the below for loop, we increase the count of the slots who are already present in the bookingCOuntTableList and
          for(int j=0;j<bookingSlotCountTableList.size();j++){
              BookingSlotCountTable bookingSlotCountTable1=bookingSlotCountTableList.get(j);
              Long slot1=bookingSlotCountTable1.getSlotId();
              if(listOfSlots.contains(slot1)) {
                 Long count=  bookingSlotCountTable1.getCount();
                 count++;
                  bookingSlotCountTable1.setCount(count);
                  bookingSlotCountTableRepository.save(bookingSlotCountTable1);
              }

          }
          //now for the elements that are not present in the bookingSloCountTable but are present in the listOfSlots ,
        //we are going to create a new entry fo those slotIds and set their count as 1;
//          ArrayList<Long>ans=new ArrayList<>();;
//          for(int k=0;k<bookingSlotCountTableList.size();k++){
//             BookingSlotCountTable bookingSlotCountTable= bookingSlotCountTableList.get(k);
//           Long slotId1=  bookingSlotCountTable.getSlotId();
//           ans.add(slotId1);
//          }
//       // List<Long> result = getUniqueElements(ans ,listOfSlots );
//        List<Long> slotList = new ArrayList<>(listOfSlots);
//          if(bookingSlotCountTableList.isEmpty() && !slotList.isEmpty()){
//              for(int i=0;i<listOfSlots.size();i++) {
//                  Long slotId = slotList.get(i);
//                  BookingSlotCountTable bookingSlotCountTable = new BookingSlotCountTable();
//                  bookingSlotCountTable.setDate(bookingRequest.getStart().toLocalDate());
//                  bookingSlotCountTable.setSlotId(slotId);
//                  bookingSlotCountTable.setEventTypeId(bookingRequest.getEventTypeId());
//                  bookingSlotCountTable.setCount((long) 1);
//                  bookingSlotCountTableRepository.save(bookingSlotCountTable);
//              }
//          }
//          else{
//              List<Long> result = getUniqueElements(ans ,listOfSlots );
//              for(int h=0;h<result.size();h++){
//                  BookingSlotCountTable bookingSlotCountTable=new BookingSlotCountTable();
//                  bookingSlotCountTable.setDate(bookingRequest.getStart().toLocalDate());
//                  bookingSlotCountTable.setEventTypeId(bookingRequest.getEventTypeId());
//                  bookingSlotCountTable.setCount((long)1);
//                  bookingSlotCountTableRepository.save(bookingSlotCountTable);
//              }
//          }
        ArrayList<Long>ans=new ArrayList<>();
        for(int h=0;h<bookingSlotCountTableList.size();h++){
            BookingSlotCountTable bookingSlotCountTable=bookingSlotCountTableList.get(h);
           Long slotId= bookingSlotCountTable.getSlotId();
           ans.add(slotId);
        }
        List<Long> result = new ArrayList<>(listOfSlots);
        result.removeAll(ans);
        //now we set the slot in bookingSlotCountTable which were not alreasy present in bookingSLotCOunTable
        for(int i=0;i<result.size();i++){
           // result.get(i);
            BookingSlotCountTable bookingSlotCountTable = new BookingSlotCountTable();
            bookingSlotCountTable.setDate(bookingRequest.getStart().toLocalDate());
            bookingSlotCountTable.setEventTypeId(bookingRequest.getEventTypeId());
            bookingSlotCountTable.setSlotId(result.get(i));
            bookingSlotCountTable.setCount((long)1);
            bookingSlotCountTableRepository.save(bookingSlotCountTable);

        }




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
            LocalDateTime endTime11=bookingRequest.getStart().plusMinutes(eventType.getLength());
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
    public static List<Long> getUniqueElements(List<Long> list1, Set<Long> set2) {
        List<Long> result = new ArrayList<>();
        for (Long element : list1) {
            if (!set2.contains(element)) {
                result.add(element);
            }
        }
        return result;
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
          UUID eventTypeId=  booking.getEventTypeId();
         LocalDate date= booking.getDate();
       List< BookingSlotCountTable> bookingSlotCountTableList=  bookingSlotCountTableRepository.findByDateAndEventTypeId(date,eventTypeId);
       for(int i=0;i<bookingSlotCountTableList.size();i++){
           BookingSlotCountTable bookingSlotCountTable=bookingSlotCountTableList.get(i);
       Long count=  bookingSlotCountTable.getCount();
       count--;
       bookingSlotCountTable.setCount(count);
       bookingSlotCountTableRepository.save(bookingSlotCountTable);
       }
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






