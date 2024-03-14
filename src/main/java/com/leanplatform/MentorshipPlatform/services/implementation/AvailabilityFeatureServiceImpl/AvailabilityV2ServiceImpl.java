package com.leanplatform.MentorshipPlatform.services.implementation.AvailabilityFeatureServiceImpl;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.entities.AvailabiliyFeature.AvailabilityV2;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.Booking;

import com.leanplatform.MentorshipPlatform.entities.OverrideAvailabilityFeature.OverrideAvailability;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Schedule;
import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.Slot;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.SlotTimeDate;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityFeatureRepository.AvailabilityV2Repository;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityFeatureRepository.DaysRepository;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.BookingRepository;
import com.leanplatform.MentorshipPlatform.repositories.EventTypeRepository.EventTypesRepository;
import com.leanplatform.MentorshipPlatform.repositories.OverrideFeatureRepository.OverrideAvailabilityRepository;
import com.leanplatform.MentorshipPlatform.repositories.ScheduleRepository.ScheduleRepository;
import com.leanplatform.MentorshipPlatform.services.AvailabilityFeatureService.AvailabilityV2Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import static com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper.containsGreaterThanOrEqualToSeven;

@Service
public class AvailabilityV2ServiceImpl implements AvailabilityV2Service {
    @Autowired
    AvailabilityV2Repository availabilityNewRepository;
    @Autowired
    DaysRepository daysRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EventTypesRepository eventTypesRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    OverrideAvailabilityRepository overrideAvailabilityRepository;


    @Transactional
    public ResponseEntity<UpdateAvailabiliityNewResponse> addAnAvailability(UUID userId, CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        if (createAvailabilityNewRequest == null || createAvailabilityNewRequest.getDays() == null || createAvailabilityNewRequest.getEndTime() == null || createAvailabilityNewRequest.getStartTime() == null ||
                createAvailabilityNewRequest.getScheduleId() == null || createAvailabilityNewRequest.getDays().isEmpty()) {
            return new ResponseEntity<>
                    (new UpdateAvailabiliityNewResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
       Schedule schedule= scheduleRepository.findByScheduleId(createAvailabilityNewRequest.getScheduleId());
        if(schedule==null){
            return new ResponseEntity<>
                    (new UpdateAvailabiliityNewResponse
                            ("0",
                                    "Schedule does not exist", null), HttpStatus.NOT_FOUND);

        }
        if(containsGreaterThanOrEqualToSeven(createAvailabilityNewRequest.getDays())){
            return new ResponseEntity<>
                    (new UpdateAvailabiliityNewResponse
                            ("0",
                                    "This day array contain day greater than equal to 7", null), HttpStatus.NOT_FOUND);
        }
//
        for (int i = 0; i < createAvailabilityNewRequest.getDays().size(); i++) {
            Long day1 = createAvailabilityNewRequest.getDays().get(i);

           AvailabilityV2 availabilityNew1 = availabilityNewRepository.findByScheduleIdAndDay(createAvailabilityNewRequest.getScheduleId(), day1);
            if (availabilityNew1 != null) {
                LocalTime startTime1 = createAvailabilityNewRequest.getStartTime().toLocalTime();
                LocalTime endTime1 = createAvailabilityNewRequest.getEndTime().toLocalTime();
                //LocalTime startTime1 = startTime.toLocalTime();
               // LocalTime endTime1 = endTime.toLocalTime();
                Set<Long> listans = availabilityNew1.getSlotIds();

                Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1, endTime1);
                listans.addAll(listOfSlots);
                availabilityNew1.setSlotIds(listans);
                availabilityNewRepository.save(availabilityNew1);
            } else {
                AvailabilityV2 availabilityNew = new AvailabilityV2();
                availabilityNew.setDay(day1);
                availabilityNew.setScheduleId(createAvailabilityNewRequest.getScheduleId());
                LocalDateTime startTime = createAvailabilityNewRequest.getStartTime();
                LocalDateTime endTime = createAvailabilityNewRequest.getEndTime();
                LocalTime startTime1 = startTime.toLocalTime();
                LocalTime endTime1 = endTime.toLocalTime();
                Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1, endTime1);
                availabilityNew.setSlotIds(listOfSlots);
                availabilityNewRepository.save(availabilityNew);

            }

        }
        ArrayList<AvailabilityV2>list=new ArrayList<>();
        List<AvailabilityV2> availabilityNewList=availabilityNewRepository.findByScheduleId(createAvailabilityNewRequest.getScheduleId());
//        for(int h=0;h<createAvailabilityNewRequest.getDays().size();h++){
//            Long day1 = createAvailabilityNewRequest.getDays().get(h);
//            AvailabilityNew availabilityNew1 = availabilityNewRepository.findByScheduleIdAndDay(createAvailabilityNewRequest.getScheduleId(), day1);
//            list.add(availabilityNew1);
//
//        }
//

   //  CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO = AvailabilityNewMapper.convertDtoToEntity(createAvailabilityNewRequest);
        UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO = AvailabilityV2Mapper.convertDtoToEntityListOfAvailability(availabilityNewList);

        return new ResponseEntity<>
                (new UpdateAvailabiliityNewResponse
                        ("1",
                                "New Availability Added", updateAvailabilityNewResponseDTO), HttpStatus.CREATED);


    }


    @Override
    public ResponseEntity<GetAllAvailabilitiesResponse> getAllAvailability(String userName, UUID userId, UUID eventTypeId, LocalDate dateFrom, LocalDate dateTo
    ) {
//        LocalDateTime startDateTime = LocalDateTime.of(2023, 12, 1, 0, 0);
//        LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 12, 23, 59, 59);

//        List<Booking> bookings = bookingRepository.findBookingsBetweenDates(startDateTime, endDateTime);
//        for(int i=0;i<bookings.size();i++){
//            Booking booking1=bookings.get(i);

//        }
       // availabilityNewRepository.findDistinctSlotIdByScheduleId()
        if(userName==null || userId==null || eventTypeId==null || dateTo==null || dateFrom==null){
            return new ResponseEntity<>(new GetAllAvailabilitiesResponse
                    (
                            "0",
                            "Invalid Request : Null object received.",null,null
                    ), HttpStatus.BAD_REQUEST);
        }
        if(dateTo.isBefore(dateFrom)){
            return new ResponseEntity<>(new GetAllAvailabilitiesResponse
                    (
                            "0",
                            "The dateTo can not be less than daterFrom",null,null
                    ), HttpStatus.BAD_REQUEST);

        }

        UUID scheduleId1 = eventTypesRepository.findScheduleIdByEventTypeId(eventTypeId);
        if(scheduleId1==null){
            return new ResponseEntity<>(new GetAllAvailabilitiesResponse
                    (
                            "1",
                            "This event does not exist ",null,null
                    ), HttpStatus.OK);
        }
        //to find out days in availability table (all the days of the week who has availabilities)
        List<Long>days=availabilityNewRepository.findAllDayByScheduleId(scheduleId1);
        //list
        List<List<Slot>> finalSetList = new ArrayList<>();
        //list
        List<List<SlotTimeDate>>finalTimeDateList=new ArrayList<>();
        //list to add days which has avaiability in (working hours)
        List<LocalDate>dates=new ArrayList<>();
        //iterate on dates
        for (LocalDate i = dateFrom;  !i.isAfter(dateTo);i= i.plusDays(1)) {
            LocalDate date = i;
            DayOfWeek day = date.getDayOfWeek();
            long dayValueInteger = day.getValue();
            //naming convention
            if(dayValueInteger==7){
                dayValueInteger=0;
            }

            long dayValueIntegerFinal=dayValueInteger;
            OverrideAvailability overrideAvailability= overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId1,i);
            if(overrideAvailability!=null && overrideAvailability.getSlotIds().size()!=0) {
                Set<Long> slotIDsList = overrideAvailability.getSlotIds();
                Set<Long> listToNotImpactOriginalList = new HashSet<>(slotIDsList);
                List<Booking> booking = bookingRepository.findAllByUserIdAndDate(userId, i);
                if (booking.size() != 0) {
                    for (int j = 0; j < booking.size(); j++) {
                        Booking booking1 = booking.get(j);
                        // Set slotIDsList= availabilityNew.getSlotIds();
                        if (booking1.getStatus() == BookingEnums.ACCEPTED) {
                            Set<Long> slotIDsList1 = booking1.getSlotIds();
                            listToNotImpactOriginalList.removeAll(slotIDsList1);
                        } else {
                            //do not substract from the list
                        }
                    }
                }
                if (!listToNotImpactOriginalList.contains((long) -1)) {
                   List<Slot> slotsLists = AvailabilityV2Mapper.catchSlotIdsListAndConvertIntoStartTimeEndTime(listToNotImpactOriginalList);
                   finalSetList.add(slotsLists);
                   dates.add(i);
                   List<SlotTimeDate> slotTimeDateList = AvailabilityV2Mapper.convertLocalTimeToLocalDateTime(slotsLists, i);
                   finalTimeDateList.add(slotTimeDateList);
                 }



            }
            else {


                AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId1, dayValueIntegerFinal);
                if (availabilityNew == null) {
                    //go to the next date;
                    continue;
                } else {

                    Set<Long> slotIDsList = availabilityNew.getSlotIds();
                    //now store this in a new list so that the original list does get impacted
                    Set<Long>listToNotImpactOriginalList=new HashSet<>(slotIDsList);
                    //fetch only date ,fetch only dateTime
                    List<Booking> booking = bookingRepository.findAllByUserIdAndDate(userId, i);
                    if (booking.size()!=0) {
                        for (int j = 0; j < booking.size(); j++) {
                            Booking booking1 = booking.get(j);
                            // Set slotIDsList= availabilityNew.getSlotIds();
                            if(booking1.getStatus()== BookingEnums.ACCEPTED) {
                                Set<Long> slotIDsList1 = booking1.getSlotIds();
                                listToNotImpactOriginalList.removeAll(slotIDsList1);
                            }
                            else{
                                //do not subtract it from the list
                            }
                        }
                    }

                        List<Slot> slotsLists = AvailabilityV2Mapper.catchSlotIdsListAndConvertIntoStartTimeEndTime(listToNotImpactOriginalList);
                        finalSetList.add(slotsLists);
                        dates.add(i);
                        List<SlotTimeDate> slotTimeDateList = AvailabilityV2Mapper.convertLocalTimeToLocalDateTime(slotsLists, i);
                        finalTimeDateList.add(slotTimeDateList);


                }
            }
        }
        List<Long> daysListDTO= AvailabilityV2Mapper.convertDaysIntoDto(days);
        List<Day>workingHours=new ArrayList<>();
        Day day=new Day();
        day.setDays(daysListDTO);
        workingHours.add(day);
        List<SlotTimeDate> ansList2 = AvailabilityV2Mapper.convertListIntoDto(finalTimeDateList);
        return new ResponseEntity<>
                (new GetAllAvailabilitiesResponse
                        ("1",
                                "Availabilites :", ansList2,workingHours), HttpStatus.OK);

    }
    @Override
   public ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailabilitys(UUID scheduleId,UUID userId,UpdateAvailabilityNewRequest updateAvailabilityNewRequest) {
        //List<AvailabilityNew> avilabilityNew1 = availabilityNewRepository.findByScheduleId(scheduleId);
       // AvailabilityNew availabilityNew = null;
       // AvailabilityNew availabilityNewtue = null;
     //   AvailabilityNew availabilityNewWed = null;
       // AvailabilityNew availabilityNewThur = null;
       // AvailabilityNew availabilityNewFri = null;
        //AvailabilityNew availabilityNewSat = null;
     //   AvailabilityNew availabilityNewSun = null;
        if(scheduleId==null || userId==null || updateAvailabilityNewRequest==null ){
            return new ResponseEntity<>(new UpdateAvailabiliityNewResponse
                    (
                            "0",
                            "Invalid Request recieved" ,
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
       Schedule schedules= scheduleRepository.findByScheduleId(scheduleId);
        if(schedules==null){
            return new ResponseEntity<>(new UpdateAvailabiliityNewResponse
                    (
                            "0",
                            "This schedule does not exist" ,
                            null
                    ), HttpStatus.NOT_FOUND);
        }
        if(updateAvailabilityNewRequest.getName()!=null){
            Schedule schedule=scheduleRepository.findByScheduleId(scheduleId);
            schedule.setName(updateAvailabilityNewRequest.getName());
            scheduleRepository.save(schedule);
        }

        if (updateAvailabilityNewRequest.getMon() != null) {
            AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 1);
            if (availabilityNew != null) {
                // Set<Long> listOfSlots=null;
                Set<Long> ans = new HashSet<>();
                for (int i = 0; i < updateAvailabilityNewRequest.getMon().size(); i++) {
                    //Set<Set<Long>> ans=new HashSet<>();
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getMon().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
                    //availabilityNew.setSlotIds(listOfSlots);
                    System.out.print(ans);
                   // availabilityNew.setSlotIds(ans);
                  //  availabilityNewRepository.save(availabilityNew);
                }
                availabilityNew.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew);
            } else {
                AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                Set<Long> ans = new HashSet<>();
                availabilityNew1.setDay((long) 1);
                availabilityNew1.setScheduleId(scheduleId);
                for (int i = 0; i < updateAvailabilityNewRequest.getMon().size(); i++) {
                    //Set<Set<Long>> ans=new HashSet<>();
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getMon().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
                  //  availabilityNew1.setSlotIds(ans);
                   // availabilityNewRepository.save(availabilityNew1);
                }
                availabilityNew1.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew1);
            }
        } else if (updateAvailabilityNewRequest.getMon() == null) {
            AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 1);
            if (availabilityNew!=null) {
                availabilityNewRepository.delete(availabilityNew);
            }
            else {
                //do nothing
            }


        }
        if (updateAvailabilityNewRequest.getTue() != null) {
            AvailabilityV2 availabilityNewtue = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 2);
            if (availabilityNewtue != null) {
                Set<Long> ans = new HashSet<>();
                for (int i = 0; i < updateAvailabilityNewRequest.getTue().size(); i++) {
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getTue().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
                    //availabilityNew.setSlotIds(listOfSlots);
                }
                availabilityNewtue.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNewtue);
            }
            else {
                AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                Set<Long> ans = new HashSet<>();
                availabilityNew1.setDay((long) 2);
                availabilityNew1.setScheduleId(scheduleId);
                for (int i = 0; i < updateAvailabilityNewRequest.getTue().size(); i++) {
                    //Set<Set<Long>> ans=new HashSet<>();
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getTue().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
//                    availabilityNew1.setSlotIds(ans);
//                    availabilityNewRepository.save(availabilityNew1);

                }
                availabilityNew1.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew1);
            }

        }
         else if(updateAvailabilityNewRequest.getTue()==null){
            AvailabilityV2 availabilityNewtue = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 2);

                if (availabilityNewtue!=null) {
                    availabilityNewRepository.delete(availabilityNewtue);
                }
                else {
                    //do nothing
                }


        }
        if (updateAvailabilityNewRequest.getWed() != null) {
            AvailabilityV2 availabilityNewWed = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 3);
            if (availabilityNewWed != null) {
                Set<Long> ans = new HashSet<>();
                for (int i = 0; i < updateAvailabilityNewRequest.getWed().size(); i++) {
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getWed().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
                    // availabilityNew.setSlotIds(listOfSlots);
                }
                availabilityNewWed.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNewWed);

            }
            else{
                    AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                    Set<Long> ans = new HashSet<>();
                    availabilityNew1.setDay((long) 3);
                    availabilityNew1.setScheduleId(scheduleId);
                    for (int i = 0; i < updateAvailabilityNewRequest.getWed().size(); i++) {
                        //Set<Set<Long>> ans=new HashSet<>();
                        Slot startTimeEndTime = updateAvailabilityNewRequest.getWed().get(i);
                        Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                        ans.addAll(listOfSlots);
//                        availabilityNew1.setSlotIds(ans);
//                        availabilityNewRepository.save(availabilityNew1);


                   }
                availabilityNew1.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew1);

            }

        }
        else if(updateAvailabilityNewRequest.getWed()==null){
            AvailabilityV2 availabilityNewWed = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 3);
            if (availabilityNewWed!=null) {
                availabilityNewRepository.delete(availabilityNewWed);
            }
            else {
                //do nothing
            }



        }
        if (updateAvailabilityNewRequest.getThur() != null) {
            AvailabilityV2 availabilityNewThur= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 4);
            if (availabilityNewThur != null) {
                Set<Long> ans = new HashSet<>();
                for (int i = 0; i < updateAvailabilityNewRequest.getThur().size(); i++) {
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getThur().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());

                    // availabilityNew.setSlotIds(listOfSlots);
                    ans.addAll(listOfSlots);
                }
                availabilityNewThur.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNewThur);

            } else {
                AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                Set<Long> ans = new HashSet<>();
                availabilityNew1.setDay((long) 4);
                availabilityNew1.setScheduleId(scheduleId);
                for (int i = 0; i < updateAvailabilityNewRequest.getThur().size(); i++) {
                    //Set<Set<Long>> ans=new HashSet<>();
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getThur().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
//                    availabilityNew1.setSlotIds(ans);
//                    availabilityNewRepository.save(availabilityNew1);


                }
                availabilityNew1.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew1);
            }
        }


            else if(updateAvailabilityNewRequest.getThur()==null){
            AvailabilityV2 availabilityNewThur= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 4);
                if (availabilityNewThur!=null) {
                    availabilityNewRepository.delete(availabilityNewThur);
                }
                else {
                    //do nothing
                }

            }

        if (updateAvailabilityNewRequest.getFri() != null) {
            AvailabilityV2 availabilityNewFri = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 5);
            if (availabilityNewFri != null) {
                Set<Long> ans = new HashSet<>();
                for (int i = 0; i < updateAvailabilityNewRequest.getFri().size(); i++) {
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getFri().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
                    // availabilityNew.setSlotIds(listOfSlots);
                }
                availabilityNewFri.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNewFri);
            } else {
                AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                Set<Long> ans = new HashSet<>();
                availabilityNew1.setDay((long) 5);
                availabilityNew1.setScheduleId(scheduleId);
                for (int i = 0; i < updateAvailabilityNewRequest.getFri().size(); i++) {
                    //Set<Set<Long>> ans=new HashSet<>();
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getFri().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
//                    availabilityNew1.setSlotIds(ans);
//                    availabilityNewRepository.save(availabilityNew1);

                }
                availabilityNew1.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew1);


            }
        }
        else if (updateAvailabilityNewRequest.getFri()==null){
            AvailabilityV2 availabilityNewFri = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 5);
                    if (availabilityNewFri!=null) {
                        availabilityNewRepository.delete(availabilityNewFri);
                    }
                    else {
                        //do nothing
                    }
        }

         if (updateAvailabilityNewRequest.getSat() != null) {
             AvailabilityV2 availabilityNewSat = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 6);
             if (availabilityNewSat != null) {
                 Set<Long> ans = new HashSet<>();
                 for (int i = 0; i < updateAvailabilityNewRequest.getSat().size(); i++) {
                     Slot startTimeEndTime = updateAvailabilityNewRequest.getSat().get(i);
                     Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                     ans.addAll(listOfSlots);
                     //  availabilityNew.setSlotIds(listOfSlots);
                 }
                 availabilityNewSat.setSlotIds(ans);
                 availabilityNewRepository.save(availabilityNewSat);
             } else {
                 AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                 Set<Long> ans = new HashSet<>();
                 availabilityNew1.setDay((long) 6);
                 availabilityNew1.setScheduleId(scheduleId);
                 for (int i = 0; i < updateAvailabilityNewRequest.getSat().size(); i++) {
                     //Set<Set<Long>> ans=new HashSet<>();
                     Slot startTimeEndTime = updateAvailabilityNewRequest.getSat().get(i);
                     Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                     ans.addAll(listOfSlots);
//                     availabilityNew1.setSlotIds(ans);
//                     availabilityNewRepository.save(availabilityNew1);


                 }
                 availabilityNew1.setSlotIds(ans);
                 availabilityNewRepository.save(availabilityNew1);

             }
         }
         else if (updateAvailabilityNewRequest.getSat()==null){
             AvailabilityV2 availabilityNewSat = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 6);
                     if (availabilityNewSat!=null) {
                         availabilityNewRepository.delete(availabilityNewSat);
                     }
                     else {
                         //do nothing
                     }


             }
        if (updateAvailabilityNewRequest.getSun() != null) {
            AvailabilityV2 availabilityNewSun = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 0);
            if (availabilityNewSun != null) {
                Set<Long> ans = new HashSet<>();
                for (int i = 0; i < updateAvailabilityNewRequest.getSun().size(); i++) {
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getSun().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
                    // availabilityNew.setSlotIds(listOfSlots);
                }
                availabilityNewSun.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNewSun);
            } else {

                AvailabilityV2 availabilityNew1 = new AvailabilityV2();
                Set<Long> ans = new HashSet<>();
                availabilityNew1.setDay((long) 0);
                availabilityNew1.setScheduleId(scheduleId);
                for (int i = 0; i < updateAvailabilityNewRequest.getSun().size(); i++) {
                    //Set<Set<Long>> ans=new HashSet<>();
                    Slot startTimeEndTime = updateAvailabilityNewRequest.getSun().get(i);
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeEndTime.getStartTime(), startTimeEndTime.getEndTime());
                    ans.addAll(listOfSlots);
//                    availabilityNew1.setSlotIds(ans);
//                    availabilityNewRepository.save(availabilityNew1);


                }
                availabilityNew1.setSlotIds(ans);
                availabilityNewRepository.save(availabilityNew1);

            }
        }
        else if (updateAvailabilityNewRequest.getSun()==null){
            AvailabilityV2 availabilityNewSun = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 0);
                if (availabilityNewSun!=null) {
                    availabilityNewRepository.delete(availabilityNewSun);
                }
//                else {
//                    //do nothing
//                }

        }
        List<AvailabilityV2> avilabilityNew1 = availabilityNewRepository.findByScheduleId(scheduleId);

        UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO = AvailabilityV2Mapper.convertDtoToEntityListOfAvailability(avilabilityNew1);
     //   UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO=null;
        return new ResponseEntity<>(new UpdateAvailabiliityNewResponse("1",
                "Availability Updated:", updateAvailabilityNewResponseDTO), HttpStatus.OK);


    }
   @Override
   public  ResponseEntity<DeleteAvailabilityResponse> deleteAvailability(UUID scheduleId,Long day){
       if(scheduleId==null || day==null){
           return new ResponseEntity<>(new DeleteAvailabilityResponse
                   (
                           "0",
                           "Invalid Request recieved"
                   ), HttpStatus.BAD_REQUEST);


       }
       Schedule schedule=scheduleRepository.findByScheduleId(scheduleId);
       if(schedule==null){
           return new ResponseEntity<>(new DeleteAvailabilityResponse
                   (
                           "0",
                           "Schedule not found"
                   ), HttpStatus.NOT_FOUND);

       }

       AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId,day);

       availabilityNewRepository.delete(availabilityNew);
       return new ResponseEntity<>(new DeleteAvailabilityResponse("1",
               "Availability deleted"), HttpStatus.OK);


   }

}








