package com.leanplatform.MentorshipPlatform.services.implementation;

import com.beust.ah.A;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponse;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestResponse;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Days;
import com.leanplatform.MentorshipPlatform.entities.Booking;

import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityNewMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityNewRepository;
import com.leanplatform.MentorshipPlatform.repositories.BookingRepository;
import com.leanplatform.MentorshipPlatform.repositories.DaysRepository;
import com.leanplatform.MentorshipPlatform.services.AvailabilityNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class AvailabilityNewServiceImpl implements AvailabilityNewService {
    @Autowired AvailabilityNewRepository availabilityNewRepository;
    @Autowired
    DaysRepository daysRepository;
    @Autowired
    BookingRepository bookingRepository;
    public ResponseEntity<CreateAvailabilityNewResponse> addAnAvailability(UUID scheduleId, CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        if(createAvailabilityNewRequest==null || createAvailabilityNewRequest.getDays()==null || createAvailabilityNewRequest.getEndTime()==null || createAvailabilityNewRequest.getStartTime()==null ||
                createAvailabilityNewRequest.getScheduleId()==null ){
            return new ResponseEntity<>
                    (new CreateAvailabilityNewResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        LocalDateTime startTime =createAvailabilityNewRequest.getStartTime();
        LocalDateTime endTime=createAvailabilityNewRequest.getEndTime();
        List<Long>listOfSlots=AvailabilityNewMapper.convertStartTimeEndTimeIntoSlots(startTime,endTime);
        List<Long>listOfDays=createAvailabilityNewRequest.getDays();
        AvailabilityNew availabilityNew=null;
        for(int i=0;i<listOfDays.size();i++){
            Long ans=listOfDays.get(i);
            availabilityNew=new AvailabilityNew();
            availabilityNew.setDay(ans);
            availabilityNew.setScheduleId(createAvailabilityNewRequest.getScheduleId());
            availabilityNew.setSlotIds(listOfSlots);
            availabilityNewRepository.save(availabilityNew);


        }

        CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO =AvailabilityNewMapper.convertDtoToEntity(availabilityNew );

        return new ResponseEntity<>
                (new CreateAvailabilityNewResponse
                        ("1",
                                "NewAvailabilityAdded", createAvailabilityNewResponseDTO), HttpStatus.CREATED);

    }




    }
//    @Override
//   public ResponseEntity<GetAllAvailabilitiesResponse>getAllAvailability(UUID scheduleId, UUID userId, UUID eventTypeId, LocalDate dateTo,LocalDate dateFrom){
//        LocalDateTime startDateTime = LocalDateTime.of(2023, 12, 1, 0, 0);
//        LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 12, 23, 59, 59);
//
//        List<Booking> bookings = bookingRepository.findBookingsBetweenDates(startDateTime, endDateTime);
//        for(int i=0;i<bookings.size();i++){
//            Booking booking1=bookings.get(i);
//        }
//
//    }


