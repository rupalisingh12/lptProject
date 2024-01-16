package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityV2;
import com.leanplatform.MentorshipPlatform.entities.Booking;

import com.leanplatform.MentorshipPlatform.entities.Schedule;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.Slot;
import com.leanplatform.MentorshipPlatform.mappers.SlotTimeDate;
import com.leanplatform.MentorshipPlatform.repositories.*;
import com.leanplatform.MentorshipPlatform.services.AvailabilityV2Service;
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
//
        for (int i = 0; i < createAvailabilityNewRequest.getDays().size(); i++) {
            Long day1 = createAvailabilityNewRequest.getDays().get(i);
           AvailabilityV2 availabilityNew1 = availabilityNewRepository.findByScheduleIdAndDay(createAvailabilityNewRequest.getScheduleId(), day1);
            if (availabilityNew1 != null) {
                LocalDateTime startTime = createAvailabilityNewRequest.getStartTime();
                LocalDateTime endTime = createAvailabilityNewRequest.getEndTime();
                LocalTime startTime1 = startTime.toLocalTime();
                LocalTime endTime1 = endTime.toLocalTime();
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
            if(day1234==7){
                day1234=0;
            }

            long day1=day1234;
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId1, day1);
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
                    List<com.leanplatform.MentorshipPlatform.mappers.Slot> slotsLists = AvailabilityV2Mapper.catchSlotIdsListAndConvertIntoStartTimeEndTime(slotIDsList);
                    finalSetList.add(slotsLists);
                    dates.add(i);
                    List<SlotTimeDate>slotTimeDateList= AvailabilityV2Mapper.convertLocalTimeToLocalDateTime(slotsLists,i);
                    finalTimeDateList.add(slotTimeDateList);

                }
            }
        }
        List<Long> days12= AvailabilityV2Mapper.convertDaysIntoDto(days);
        List<Day>workingHours=new ArrayList<>();
        Day day=new Day();
        day.setDays(days12);
        workingHours.add(day);
        List<SlotTimeDate> ansList2 = AvailabilityV2Mapper.convertListIntoDto(finalTimeDateList);
        return new ResponseEntity<>
                (new GetAllAvailabilitiesResponse
                        ("1",
                                "Availabilites :", ansList2,workingHours), HttpStatus.OK);

    }
    @Override
   public ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailabilitys(UUID scheduleId, UpdateAvailabilityNewRequest updateAvailabilityNewRequest) {
        //List<AvailabilityNew> avilabilityNew1 = availabilityNewRepository.findByScheduleId(scheduleId);
       // AvailabilityNew availabilityNew = null;
       // AvailabilityNew availabilityNewtue = null;
     //   AvailabilityNew availabilityNewWed = null;
       // AvailabilityNew availabilityNewThur = null;
       // AvailabilityNew availabilityNewFri = null;
        //AvailabilityNew availabilityNewSat = null;
     //   AvailabilityNew availabilityNewSun = null;


        if (updateAvailabilityNewRequest.getMon() != null) {
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 1);
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
                com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 1);
            if (availabilityNew!=null) {
                availabilityNewRepository.delete(availabilityNew);
            }
            else {
                //do nothing
            }


        }
        if (updateAvailabilityNewRequest.getTue() != null) {
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewtue = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 2);
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
                com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewtue = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 2);

                if (availabilityNewtue!=null) {
                    availabilityNewRepository.delete(availabilityNewtue);
                }
                else {
                    //do nothing
                }


        }
        if (updateAvailabilityNewRequest.getWed() != null) {
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewWed = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 3);
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
                    com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewWed = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 3);
            if (availabilityNewWed!=null) {
                availabilityNewRepository.delete(availabilityNewWed);
            }
            else {
                //do nothing
            }



        }
        if (updateAvailabilityNewRequest.getThur() != null) {
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewThur= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 4);
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
                com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewThur= availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 4);
                if (availabilityNewThur!=null) {
                    availabilityNewRepository.delete(availabilityNewThur);
                }
                else {
                    //do nothing
                }

            }

        if (updateAvailabilityNewRequest.getFri() != null) {
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewFri = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 5);
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
                com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewFri = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 5);
                    if (availabilityNewFri!=null) {
                        availabilityNewRepository.delete(availabilityNewFri);
                    }
                    else {
                        //do nothing
                    }
        }

         if (updateAvailabilityNewRequest.getSat() != null) {
             com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewSat = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 6);
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
                 com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
             com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewSat = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 6);
                     if (availabilityNewSat!=null) {
                         availabilityNewRepository.delete(availabilityNewSat);
                     }
                     else {
                         //do nothing
                     }


             }
        if (updateAvailabilityNewRequest.getSun() != null) {
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewSun = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 0);
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

                com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew1 = new com.leanplatform.MentorshipPlatform.entities.AvailabilityV2();
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
            com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNewSun = availabilityNewRepository.findByScheduleIdAndDay(scheduleId, (long) 0);
                if (availabilityNewSun!=null) {
                    availabilityNewRepository.delete(availabilityNewSun);
                }
//                else {
//                    //do nothing
//                }

        }
        List<com.leanplatform.MentorshipPlatform.entities.AvailabilityV2> avilabilityNew1 = availabilityNewRepository.findByScheduleId(scheduleId);

        UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO = AvailabilityV2Mapper.convertDtoToEntityListOfAvailability(avilabilityNew1);
     //   UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO=null;
        return new ResponseEntity<>(new UpdateAvailabiliityNewResponse("1",
                "Availability Updated:", updateAvailabilityNewResponseDTO), HttpStatus.OK);


    }
   @Override
   public  ResponseEntity<DeleteAvailabilityResponse> deleteAvailability(UUID scheduleId,Long day){
       com.leanplatform.MentorshipPlatform.entities.AvailabilityV2 availabilityNew = availabilityNewRepository.findByScheduleIdAndDay(scheduleId,day);
       availabilityNewRepository.delete(availabilityNew);
       return new ResponseEntity<>(new DeleteAvailabilityResponse("1",
               "Availability deleted"), HttpStatus.OK);


   }

}








