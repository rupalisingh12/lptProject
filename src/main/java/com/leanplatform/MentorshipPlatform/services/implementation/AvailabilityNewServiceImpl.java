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
import com.leanplatform.MentorshipPlatform.repositories.*;
import com.leanplatform.MentorshipPlatform.services.AvailabilityNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
public class AvailabilityNewServiceImpl implements AvailabilityNewService {
    @Autowired
    AvailabilityNewRepository availabilityNewRepository;
    @Autowired
    DaysRepository daysRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    EventTypesRepository eventTypesRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    public ResponseEntity<CreateAvailabilityNewResponse> addAnAvailability(UUID scheduleId, CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        if (createAvailabilityNewRequest == null || createAvailabilityNewRequest.getDays() == null || createAvailabilityNewRequest.getEndTime() == null || createAvailabilityNewRequest.getStartTime() == null ||
                createAvailabilityNewRequest.getScheduleId() == null) {
            return new ResponseEntity<>
                    (new CreateAvailabilityNewResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
//
//        for (int i = 0; i < createAvailabilityNewRequest.getDays().size(); i++) {
//            Long day1 = createAvailabilityNewRequest.getDays().get(i);
//            AvailabilityNew availabilityNew1 = availabilityNewRepository.findByScheduleIdAndDay(createAvailabilityNewRequest.getScheduleId(), day1);
//            if (availabilityNew1 != null) {
//                LocalDateTime startTime = createAvailabilityNewRequest.getStartTime();
//                LocalDateTime endTime = createAvailabilityNewRequest.getEndTime();
//                LocalTime startTime1 = startTime.toLocalTime();
//                LocalTime endTime1 = endTime.toLocalTime();
//                Set<Long> listans = availabilityNew1.getSlotIds();
//
//                Set<Long> listOfSlots = slotRepository.findSlotIdsByTimeRange(startTime1, endTime1);
//                listans.addAll(listOfSlots);
//                availabilityNew1.setSlotIds(listans);
//                availabilityNewRepository.save(availabilityNew1);
//            } else {
//                AvailabilityNew availabilityNew = new AvailabilityNew();
//                availabilityNew.setDay(day1);
//                availabilityNew.setScheduleId(createAvailabilityNewRequest.getScheduleId());
//                LocalDateTime startTime = createAvailabilityNewRequest.getStartTime();
//                LocalDateTime endTime = createAvailabilityNewRequest.getEndTime();
//                LocalTime startTime1 = startTime.toLocalTime();
//                LocalTime endTime1 = endTime.toLocalTime();
//                Set<Long> listOfSlots = slotRepository.findSlotIdsByTimeRange(startTime1, endTime1);
//                availabilityNew.setSlotIds(listOfSlots);
//                availabilityNewRepository.save(availabilityNew);
//
//            }

        }
//        LocalDateTime startTime =createAvailabilityNewRequest.getStartTime();
//        LocalDateTime endTime=createAvailabilityNewRequest.getEndTime();
//        LocalTime startTime1 = startTime.toLocalTime();
//        LocalTime endTime1 = endTime.toLocalTime();


//
//        List<Long>listOfSlots=slotRepository.findSlotIdsByTimeRange(startTime1,endTime1);
//
////        List<Long>listOfSlots=AvailabilityNewMapper.convertStartTimeEndTimeIntoSlots(startTime,endTime);
//
//        List<Long>listOfDays=createAvailabilityNewRequest.getDays();
//        AvailabilityNew availabilityNew=null;
//        for(int i=0;i<listOfDays.size();i++){
//            Long ans=listOfDays.get(i);
//            availabilityNew=new AvailabilityNew();
//            availabilityNew.setDay(ans);
//            availabilityNew.setScheduleId(createAvailabilityNewRequest.getScheduleId());
//            availabilityNew.setSlotIds(listOfSlots);
//            availabilityNewRepository.save(availabilityNew);
//
//
//        }

        CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO = AvailabilityNewMapper.convertDtoToEntity(createAvailabilityNewRequest);

        return new ResponseEntity<>
                (new CreateAvailabilityNewResponse
                        ("1",
                                "NewAvailabilityAdded", createAvailabilityNewResponseDTO), HttpStatus.CREATED);

    }


//    @Override
//    public ResponseEntity<GetAllAvailabilitiesResponse> getAllAvailability(String userName, UUID userId, UUID eventTypeId, LocalDate dateTo, LocalDate dateFrom) {
////        LocalDateTime startDateTime = LocalDateTime.of(2023, 12, 1, 0, 0);
////        LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 12, 23, 59, 59);
//
////        List<Booking> bookings = bookingRepository.findBookingsBetweenDates(startDateTime, endDateTime);
////        for(int i=0;i<bookings.size();i++){
////            Booking booking1=bookings.get(i);
//
////        }
//        UUID scheduleId1=eventTypesRepository.findScheduleIdByEventTypeId(eventTypeId);
//          UUID userID=scheduleRepository.findByScheduleId(scheduleId1);
//       for(LocalDate i=dateTo;i.isBefore(dateFrom);i.plusDays(1)){
//           LocalDate date=i;
//           DayOfWeek day=date.getDayOfWeek();
//           long day1=day.getValue();
//           AvailabilityNew availabilityNew=availabilityNewRepository.findByScheduleIdAndDay(scheduleId1,day1);
//           Booking booking=bookingRepository.findAllByUserIdAndDate(scheduleId1,i);
//           if(booking!=null){
//               availabilityNew.getSlotIds().removeAll(booking.getSlotIds());
//
//           }
//
//       }



}


