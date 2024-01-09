package com.leanplatform.MentorshipPlatform.services.implementation;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponse;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Booking;

import com.leanplatform.MentorshipPlatform.mappers.AvailabilityNewMapper;
import com.leanplatform.MentorshipPlatform.mappers.Slot;
import com.leanplatform.MentorshipPlatform.mappers.SlotTimeDate;
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
import java.util.*;

@Service
public class AvailabilityNewServiceImpl implements AvailabilityNewService {
    @Autowired
    AvailabilityNewRepository availabilityNewRepository;
    @Autowired
    DaysRepository daysRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EventTypesRepository eventTypesRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    public ResponseEntity<UpdateAvailabiliityNewResponse> addAnAvailability(UUID scheduleId, CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        if (createAvailabilityNewRequest == null || createAvailabilityNewRequest.getDays() == null || createAvailabilityNewRequest.getEndTime() == null || createAvailabilityNewRequest.getStartTime() == null ||
                createAvailabilityNewRequest.getScheduleId() == null) {
            return new ResponseEntity<>
                    (new UpdateAvailabiliityNewResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
//
        for (int i = 0; i < createAvailabilityNewRequest.getDays().size(); i++) {
            Long day1 = createAvailabilityNewRequest.getDays().get(i);
            AvailabilityNew availabilityNew1 = availabilityNewRepository.findByScheduleIdAndDay(createAvailabilityNewRequest.getScheduleId(), day1);
            if (availabilityNew1 != null) {
                LocalDateTime startTime = createAvailabilityNewRequest.getStartTime();
                LocalDateTime endTime = createAvailabilityNewRequest.getEndTime();
                LocalTime startTime1 = startTime.toLocalTime();
                LocalTime endTime1 = endTime.toLocalTime();
                Set<Long> listans = availabilityNew1.getSlotIds();

                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTime1, endTime1);
                listans.addAll(listOfSlots);
                availabilityNew1.setSlotIds(listans);
                availabilityNewRepository.save(availabilityNew1);
            } else {
                AvailabilityNew availabilityNew = new AvailabilityNew();
                availabilityNew.setDay(day1);
                availabilityNew.setScheduleId(createAvailabilityNewRequest.getScheduleId());
                LocalDateTime startTime = createAvailabilityNewRequest.getStartTime();
                LocalDateTime endTime = createAvailabilityNewRequest.getEndTime();
                LocalTime startTime1 = startTime.toLocalTime();
                LocalTime endTime1 = endTime.toLocalTime();
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTime1, endTime1);
                availabilityNew.setSlotIds(listOfSlots);
                availabilityNewRepository.save(availabilityNew);

            }

        }
        ArrayList<AvailabilityNew>list=new ArrayList<>();
        for(int h=0;h<createAvailabilityNewRequest.getDays().size();h++){
            Long day1 = createAvailabilityNewRequest.getDays().get(h);
            AvailabilityNew availabilityNew1 = availabilityNewRepository.findByScheduleIdAndDay(createAvailabilityNewRequest.getScheduleId(), day1);
            list.add(availabilityNew1);

        }
//

      //  CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO = AvailabilityNewMapper.convertDtoToEntity(createAvailabilityNewRequest);
        UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO = AvailabilityNewMapper.convertDtoToEntityListOfAvailability(list);

        return new ResponseEntity<>
                (new UpdateAvailabiliityNewResponse
                        ("1",
                                "New AvailabilityAdded", updateAvailabilityNewResponseDTO), HttpStatus.CREATED);


    }


    @Override
    public ResponseEntity<GetAllAvailabilitiesResponse> getAllAvailability(String userName, UUID userId, UUID eventTypeId, LocalDate dateTo, LocalDate dateFrom) {
//        LocalDateTime startDateTime = LocalDateTime.of(2023, 12, 1, 0, 0);
//        LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 12, 23, 59, 59);

//        List<Booking> bookings = bookingRepository.findBookingsBetweenDates(startDateTime, endDateTime);
//        for(int i=0;i<bookings.size();i++){
//            Booking booking1=bookings.get(i);

//        }
       // availabilityNewRepository.findDistinctSlotIdByScheduleId()
        UUID scheduleId1 = eventTypesRepository.findScheduleIdByEventTypeId(eventTypeId);
        List<Long>days=availabilityNewRepository.findAllDayByScheduleId(scheduleId1);
        List<List<com.leanplatform.MentorshipPlatform.mappers.Slot>> finalSetList = new ArrayList<>();
        List<List<SlotTimeDate>>finalTimeDateList=new ArrayList<>();
        List<LocalDate>dates=new ArrayList<>();
        for (LocalDate i = dateTo;  !i.isAfter(dateFrom);i= i.plusDays(1)) {
            LocalDate date = i;
            DayOfWeek day = date.getDayOfWeek();
            long day1234 = day.getValue();
            //naming convention

            long day1=day1234-1;
            AvailabilityNew availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId1, day1);
            if(availabilityNew==null){
                //go to the next date;
                continue;
            }
            else {

                Set<Long> slotIDsList = availabilityNew.getSlotIds();
                //fetch only date ,fetch only dateTime
                List<Booking> booking = bookingRepository.findAllByUserIdAndDate(userId, i);
                if (booking != null) {
                    for (int j = 0; j < booking.size(); j++) {
                        Booking booking1 = booking.get(j);
                        // Set slotIDsList= availabilityNew.getSlotIds();
                        Set<Long> slotIDsList1 = booking1.getSlotIds();
                        slotIDsList.removeAll(slotIDsList1);
                    }
                    List<com.leanplatform.MentorshipPlatform.mappers.Slot> slotsLists = AvailabilityNewMapper.catchSlotIdsListAndConvertIntoStartTimeEndTime(slotIDsList);
                    finalSetList.add(slotsLists);
                    dates.add(i);
                    List<SlotTimeDate>slotTimeDateList= AvailabilityNewMapper.convertLocalTimeToLocalDateTime(slotsLists,i);
                    finalTimeDateList.add(slotTimeDateList);

                }
            }
        }
        List<Long> days12=AvailabilityNewMapper.convertDaysIntoDto(days);
        List<Day>workingHours=new ArrayList<>();
        Day day=new Day();
        day.setDay(days12);
        workingHours.add(day);
        List<SlotTimeDate> ansList2 = AvailabilityNewMapper.convertListIntoDto(finalTimeDateList);
        return new ResponseEntity<>
                (new GetAllAvailabilitiesResponse
                        ("1",
                                "DateRange:", ansList2,workingHours), HttpStatus.OK);

    }
    @Override
   public ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailabilitys(UUID scheduleId, UpdateAvailabilityNewRequest updateAvailabilityNewRequest){
        List<AvailabilityNew> avilabilityNew1=availabilityNewRepository.findByScheduleId(scheduleId);
        if(updateAvailabilityNewRequest.getMon()!=null) {
            AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)0);
            if(availabilityNew!=null){
               // Set<Long> listOfSlots=null;
                Set<Long> ans=new HashSet<>();
            for(int i=0;i<updateAvailabilityNewRequest.getMon().size();i++) {
                //Set<Set<Long>> ans=new HashSet<>();
                Slot startTimeEndTime = updateAvailabilityNewRequest.getMon().get(i);
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                ans.addAll(listOfSlots);
                //availabilityNew.setSlotIds(listOfSlots);
                System.out.print(ans);
            }
                availabilityNew.setSlotIds(ans);
            availabilityNewRepository.save(availabilityNew);
            }
        }
        if(updateAvailabilityNewRequest.getTue()!=null){
            AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)1);
            if(availabilityNew!=null){
                Set<Long> ans=new HashSet<>();
            for(int i=0;i<updateAvailabilityNewRequest.getTue().size();i++) {
                Slot startTimeEndTime = updateAvailabilityNewRequest.getTue().get(i);
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                ans.addAll(listOfSlots);
                //availabilityNew.setSlotIds(listOfSlots);
            }
                availabilityNew.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew);
            }

        }
        if(updateAvailabilityNewRequest.getWed()!=null){
            AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)2);
            if(availabilityNew!=null){
                Set<Long> ans=new HashSet<>();
            for(int i=0;i<updateAvailabilityNewRequest.getWed().size();i++) {
                Slot startTimeEndTime = updateAvailabilityNewRequest.getWed().get(i);
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                ans.addAll(listOfSlots);
               // availabilityNew.setSlotIds(listOfSlots);
            }
                availabilityNew.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew);

           }

        }
        if(updateAvailabilityNewRequest.getThur()!=null){
            AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)3);
            if(availabilityNew!=null){
                Set<Long> ans=new HashSet<>();
            for(int i=0;i<updateAvailabilityNewRequest.getThur().size();i++) {
                Slot startTimeEndTime = updateAvailabilityNewRequest.getThur().get(i);
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());

               // availabilityNew.setSlotIds(listOfSlots);
                ans.addAll(listOfSlots);
            }
                availabilityNew.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew);

            }

        }
        if(updateAvailabilityNewRequest.getFri()!=null){
            AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)4);
            if(availabilityNew!=null){
                Set<Long> ans=new HashSet<>();
            for(int i=0;i<updateAvailabilityNewRequest.getFri().size();i++) {
                Slot startTimeEndTime = updateAvailabilityNewRequest.getFri().get(i);
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                ans.addAll(listOfSlots);
               // availabilityNew.setSlotIds(listOfSlots);
            }
                availabilityNew.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew);
            }



        }
        if(updateAvailabilityNewRequest.getSat()!=null){
            AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)5);
            if(availabilityNew!=null){
                Set<Long> ans=new HashSet<>();
            for(int i=0;i<updateAvailabilityNewRequest.getSat().size();i++) {
                Slot startTimeEndTime = updateAvailabilityNewRequest.getSat().get(i);
                Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                ans.addAll(listOfSlots);
              //  availabilityNew.setSlotIds(listOfSlots);
            }
                availabilityNew.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew);
            }

        }
       if(updateAvailabilityNewRequest.getSun()!=null){
           AvailabilityNew availabilityNew= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long)6);
           if(availabilityNew!=null){
               Set<Long> ans=new HashSet<>();
           for(int i=0;i<updateAvailabilityNewRequest.getSun().size();i++) {
               Slot startTimeEndTime = updateAvailabilityNewRequest.getSun().get(i);
               Set<Long> listOfSlots = AvailabilityNewMapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
               ans.addAll(listOfSlots);
              // availabilityNew.setSlotIds(listOfSlots);
           }
               availabilityNew.setSlotIds(ans);
               availabilityNewRepository.save(availabilityNew);
           }

       }
       UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO = AvailabilityNewMapper.convertDtoToEntityListOfAvailability(avilabilityNew1);
       return new ResponseEntity<>(new UpdateAvailabiliityNewResponse("1",
                               "Availability Updated:",updateAvailabilityNewResponseDTO), HttpStatus.OK);


   }
   @Override
   public  ResponseEntity<DeleteAvailabilityResponse> deleteAvailability(UUID scheduleId,Long day){
       AvailabilityNew availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId,day);
       availabilityNewRepository.delete(availabilityNew);
       return new ResponseEntity<>(new DeleteAvailabilityResponse("1",
               "Availability deleted"), HttpStatus.OK);


   }

}








