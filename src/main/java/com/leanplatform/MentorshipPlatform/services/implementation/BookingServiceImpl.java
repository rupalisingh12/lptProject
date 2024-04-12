package com.leanplatform.MentorshipPlatform.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanplatform.MentorshipPlatform.CalendarQuickstart;
import com.leanplatform.MentorshipPlatform.dto.BookingController.*;
import com.leanplatform.MentorshipPlatform.entities.AccessToken;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.Booking;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.BookingSlotCountTable;
import com.leanplatform.MentorshipPlatform.entities.EventTypeFeature.EventType;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Attendee;
import com.leanplatform.MentorshipPlatform.entities.User.Creator;
import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.BookingFunctnalityMapper.BookingMapper;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.AccessTokenRepository;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.BookingRepository;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.BookingSlotCountTableRepository;
import com.leanplatform.MentorshipPlatform.repositories.EventTypeRepository.EventTypesRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.AttendeeRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.LocationRepository;
import com.leanplatform.MentorshipPlatform.repositories.User.CreatorRepository;
import com.leanplatform.MentorshipPlatform.services.BookingFeatureService.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
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
    private CreatorRepository creatorRepository;
    @Autowired
    AccessTokenRepository accessTokenRepository;
    @Autowired
    LocationRepository location;
    @Autowired
    AttendeeRepository attendeeRepository;
    @Autowired
    EventTypesRepository eventTypesRepository;
    @Autowired CalendarQuickstart calendarQuickstart;
    String clientId = "418895730508-igvn95potkq4b0626s3hsise209i5rbr.apps.googleusercontent.com";
    String clientSecret = "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";
  //  String code = "YOUR_AUTHORIZATION_CODE"; // Replace with your actual authorization code
    String redirectUri = "http://localhost:8888/Callback"; // Make sure this matches one of your registered redirect URIs


    private static final ObjectMapper objectMapper = new ObjectMapper();
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
        List<Booking> bookingList =bookingRepository.findBookingsByCriteria(bookingRequest.getEventTypeId(), bookingRequest.getStart().toLocalDate(),bookingRequest.getResponse().getEmail());
        for(int i=0;i<bookingList.size();i++){
            Booking booking= bookingList.get(i);
            if(booking.getStatus().equals(BookingEnums.ACCEPTED)) {
                Set<Long> slotIds = booking.getSlotIds();
                Set<Long> commonElements = new HashSet<>(listOfSlots);
                // Retain only the common elements in the copy
                commonElements.retainAll(slotIds);
                if (!commonElements.isEmpty()) {
                    return new ResponseEntity<>(new CreateBookingResponse
                            (
                                    "0",
                                    "This mentee has booked the same slot for a same eventTypeId and same date .",
                                    null
                            ), HttpStatus.BAD_REQUEST);

                } else {
                    //continue
                }
            }

        }

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
        EventType eventType1= eventTypesRepository.findByEventId(bookingRequest.getEventTypeId());
        Long noOfStudents=  eventType1.getNoOfStudents();
        if(noOfStudents!=null){
        if(noOfStudents!=1) {

            ArrayList<Long> ans = new ArrayList<>();
            for (int h = 0; h < bookingSlotCountTableList.size(); h++) {
                BookingSlotCountTable bookingSlotCountTable = bookingSlotCountTableList.get(h);
                Long slotId = bookingSlotCountTable.getSlotId();
                ans.add(slotId);
            }
            List<Long> result = new ArrayList<>(listOfSlots);
            result.removeAll(ans);
            //now we set the slot in bookingSlotCountTable which were not alreasy present in bookingSLotCOunTable
            for (int i = 0; i < result.size(); i++) {
                // result.get(i);
                BookingSlotCountTable bookingSlotCountTable = new BookingSlotCountTable();
                bookingSlotCountTable.setDate(bookingRequest.getStart().toLocalDate());
                bookingSlotCountTable.setEventTypeId(bookingRequest.getEventTypeId());
                bookingSlotCountTable.setSlotId(result.get(i));
                bookingSlotCountTable.setCount((long) 1);
                bookingSlotCountTableRepository.save(bookingSlotCountTable);

            }
        }
        if(noOfStudents==1){
            List<Booking>bookings= bookingRepository.findByEventIdAndDate(bookingRequest.getEventTypeId(),bookingRequest.getStart().toLocalDate());
            if(!bookings.isEmpty()){
                for(int i=0;i<bookings.size();i++){
                  Booking booking1 = bookings.get(i);
                    BookingEnums bookingEnums= booking1.getStatus();
                    bookingEnums.compareTo(BookingEnums.ACCEPTED);
                    Booking ans = bookings.get(i);
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
            }
            else {
//                List<Booking> bookingList =bookingRepository.findBookingsByCriteria(bookingRequest.getEventTypeId(), bookingRequest.getStart().toLocalDate(),bookingRequest.getResponse().getEmail());
//                for(int i=0;i<bookingList.size();i++){
//                    Booking booking= bookingList.get(i);
//                    if(booking.getStatus().equals(BookingEnums.ACCEPTED)) {
//                        Set<Long> slotIds = booking.getSlotIds();
//                        Set<Long> commonElements = new HashSet<>(listOfSlots);
//                        // Retain only the common elements in the copy
//                        commonElements.retainAll(slotIds);
//                        if (!commonElements.isEmpty()) {
//                            return new ResponseEntity<>(new CreateBookingResponse
//                                    (
//                                            "0",
//                                            "This mentee has booked the same slot for a same eventTypeId and same date .",
//                                            null
//                                    ), HttpStatus.BAD_REQUEST);
//
//                        } else {
//                            //continue
//                        }
//                    }
//
//                }
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
                try {
                    calendarQuickstart.createCalendarEvent(userId, attendee,booking);
                } catch (GeneralSecurityException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                CreateBookingDTO createBookingDTO1 = BookingMapper.convertEntityToDto(booking, userE, attendeesList);
                return new ResponseEntity<>(new
                        CreateBookingResponse("1",
                        "Booking Created", createBookingDTO1), HttpStatus.OK);

            }
        }
//        List<Booking> bookingList =bookingRepository.findBookingsByCriteria(bookingRequest.getEventTypeId(), bookingRequest.getStart().toLocalDate(),bookingRequest.getResponse().getEmail());
//        for(int i=0;i<bookingList.size();i++){
//           Booking booking= bookingList.get(i);
//           if(booking.getStatus().equals(BookingEnums.ACCEPTED)) {
//               Set<Long> slotIds = booking.getSlotIds();
//               Set<Long> commonElements = new HashSet<>(listOfSlots);
//               // Retain only the common elements in the copy
//               commonElements.retainAll(slotIds);
//               if (!commonElements.isEmpty()) {
//                   return new ResponseEntity<>(new CreateBookingResponse
//                           (
//                                   "0",
//                                   "This mentee has booked the same slot for a same eventTypeId and same date .",
//                                   null
//                           ), HttpStatus.BAD_REQUEST);
//
//               } else {
//                   //continue
//               }
//           }
//
//        }
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
           try {
            calendarQuickstart.createCalendarEvent(userId, attendee,booking );
           } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
           } catch (IOException e) {
            throw new RuntimeException(e);
           }
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
    @Override
    public ResponseEntity<GetMenteeWhoBookedSameSlotResponse>getMentee(UUID eventTypeId, LocalDateTime startTime,LocalDateTime endTime){
        if(eventTypeId==null || startTime==null || endTime==null ){
            return new ResponseEntity<>(new
                    GetMenteeWhoBookedSameSlotResponse ("0", "Invalid Request",null), HttpStatus.BAD_REQUEST);
        }
        List<Attendee> attendeesList= attendeeRepository.findAttendeesByCriteria( eventTypeId, startTime,  endTime,BookingEnums.ACCEPTED);
       ArrayList<AttendeeDetailsDTO>attendeeDetailsDTOSList=new ArrayList<>();
        for(int i=0;i<attendeesList.size();i++){
           Attendee attendee = attendeesList.get(i);
            AttendeeDetailsDTO attendeeDetailsDTO= BookingMapper.convertEntityToDtoA1(attendee);
            attendeeDetailsDTOSList.add(attendeeDetailsDTO);

        }
        return new ResponseEntity<>(new
                GetMenteeWhoBookedSameSlotResponse ("1", "The attendee List is",attendeeDetailsDTOSList), HttpStatus.OK);


    }
    public ResponseEntity<GetMenteeWhoBookedSameSlotResponse>CreateMeetingLink(UUID userId){

        try {
            List<Attendee> attendee =null;
             Booking booking=null;
            calendarQuickstart.createCalendarEvent(userId,attendee,booking);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(new
                GetMenteeWhoBookedSameSlotResponse ("1", "The attendee List is",null), HttpStatus.OK);

    }
    public  ResponseEntity<getAccessToken>getAccessTokenAndRefreshToken(getACcessTokenPayLoad getACcessTokenPayLoad){
        if(getACcessTokenPayLoad==null){
            return new ResponseEntity<>(new getAccessToken
                     ("0", "Invalid Request"), HttpStatus.BAD_REQUEST);
        }
        AccessToken accessTokenTable=new AccessToken();
       // accessTokenTable.setAccessToken(getACcessTokenPayLoad.getAccess_token());
        accessTokenTable.setTokenType(getACcessTokenPayLoad.getToken_type());
        accessTokenTable.setRefreshToken(getACcessTokenPayLoad.getRefresh_token());
        accessTokenTable.setUserId(accessTokenTable.getUserId());
        accessTokenTable.setScope(accessTokenTable.getScope());

        return new ResponseEntity<>(new  getAccessToken
                ("1", "The attendee List is"), HttpStatus.OK);



    }
    public ResponseEntity<AccessTokenResponse>getCodeAndCOnvertItToAccesToken(String code) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Creator creator = creatorRepository.findByEmail(username);
        UUID creatorId =creator.getId();
        try {
            URL url = new URL("https://oauth2.googleapis.com/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Construct the request body
            String requestBody = "client_id=" + clientId +
                    "&client_secret=" + clientSecret +
                    "&code=" + code +
                    "&redirect_uri=" + redirectUri +
                    "&grant_type=authorization_code";

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the response JSON into GetAccessToken object
                AccessTokenResponse getAccessToken = objectMapper.readValue(response.toString(), AccessTokenResponse.class);

                // Return the response entity with parsed GetAccessToken
                AccessToken accessTokenTable=new AccessToken();
                accessTokenTable.setRefreshToken(getAccessToken.getRefreshToken());
                accessTokenTable.setUserId(creatorId);
                accessTokenRepository.save(accessTokenTable);

                return ResponseEntity.ok(getAccessToken);
            } else {
                // Handle error response
                return ResponseEntity.status(responseCode).build(); // Return appropriate error response
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return appropriate error response
        }

    }


    }











