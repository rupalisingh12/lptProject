package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.*;
import com.leanplatform.MentorshipPlatform.entities.OverrideAvailabilityFeature.OverrideAvailability;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.OverrideAvailabilityMapper.OverrideAvailabilityMapper;
import com.leanplatform.MentorshipPlatform.repositories.OverrideFeatureRepository.OverrideAvailabilityRepository;
import com.leanplatform.MentorshipPlatform.services.OverrideAvailabilityFeatureService.OverrideAvailabilityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDate;
@Service
public class OverrideAvailabilityServiceImpl implements OverrideAvailabilityService {

    @Autowired
    OverrideAvailabilityRepository overrideAvailabilityRepository;

    //    @Override
//    public ResponseEntity<AddOverrideAvailabilityRespone> addOverrideAvailability(UUID scheduleId, List<AddOverrideAvailabilityRequest> addOverrideAvailabilityRequest){
//        if(scheduleId==null ||addOverrideAvailabilityRequest==null){
//            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0","Invalid Request",null), HttpStatus.BAD_REQUEST);
//        }
//        OverrideAvailability overrideAvailability = new OverrideAvailability();
//        Set<Long>ans=new HashSet<>();
//
//        overrideAvailability.setScheduleId(scheduleId);
//        overrideAvailability.setDate(addOverrideAvailabilityRequest.get(0).getStartTime().toLocalDate());
//        for(int i=0;i<addOverrideAvailabilityRequest.size();i++) {
//            AddOverrideAvailabilityRequest overrideAvailability1 = addOverrideAvailabilityRequest.get(i);
//            LocalTime startTimeAns = overrideAvailability1.getStartTime().toLocalTime();
//            LocalTime endTimeAns = overrideAvailability1.getEndTime().toLocalTime();
//            Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTimeAns, endTimeAns);
//            ans.addAll(listOfSlots);
//        }
//        overrideAvailability.setSlotIds(ans);
//        overrideAvailabilityRepository.save(overrideAvailability);
//        List<AddOverrideAvailabilityResponseDTO> ansList= OverrideAvailabilityMapper.convertEntityToDTO( overrideAvailability);
//        return new ResponseEntity<>
//                (new AddOverrideAvailabilityRespone
//                        ("1",
//                                "The override availabilites are",ansList),HttpStatus.CREATED);
//
//
//
//
//
//    }
    @Transactional
    @Override
    public ResponseEntity<AddOverrideAvailabilityRespone> UpdateOverrideAvailability(UUID scheduleId, AddavailabilityOverrideCombinedRequest addavailabilityOverrideCombinedRequest) {
        if (scheduleId == null || addavailabilityOverrideCombinedRequest == null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        if(!addavailabilityOverrideCombinedRequest.getAddOverrideUnavailabiltyRequest().isEmpty()){
            for(int i=0;i<addavailabilityOverrideCombinedRequest.getAddOverrideUnavailabiltyRequest().size();i++){
                LocalDate dateOfUnavailaledte=addavailabilityOverrideCombinedRequest.getAddOverrideUnavailabiltyRequest().get(i).getDate();
                OverrideAvailability overrideAvailability1 = overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId,dateOfUnavailaledte);
                if(overrideAvailability1==null){
                    OverrideAvailability overrideAvailability = new OverrideAvailability();
                    overrideAvailability.setDate(dateOfUnavailaledte);
                    overrideAvailability.setScheduleId(scheduleId);
                    Set<Long>unavailabilityList=new HashSet<>();
                    unavailabilityList.add((long)-1);
                    overrideAvailability.setSlotIds(unavailabilityList);
                    overrideAvailabilityRepository.save(overrideAvailability);

                }
                else{
                    Set<Long>unavailabilityList=new HashSet<>();
                    unavailabilityList.add((long)-1);
                    overrideAvailability1.setSlotIds(unavailabilityList);
                    overrideAvailabilityRepository.save(overrideAvailability1);
                }
            }

        }
        Set<Long>ansListToStoreFinalAnswer=new HashSet<>();
        Set<LocalDate>datesArray=new HashSet<>();
        for (int i = 0; i < addavailabilityOverrideCombinedRequest.getAddOverrideAvailabilityRequest().size(); i++) {
            LocalDateTime startTime1 = addavailabilityOverrideCombinedRequest.getAddOverrideAvailabilityRequest().get(i).getStartTimeSlot2();
            LocalDateTime endTime1 = addavailabilityOverrideCombinedRequest.getAddOverrideAvailabilityRequest().get(i).getEndTimeSlot2();
            if(!datesArray.contains(startTime1.toLocalDate())) {
                datesArray.add(startTime1.toLocalDate());
                OverrideAvailability overrideAvailability1 = overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId,startTime1.toLocalDate());
                if (overrideAvailability1 == null) {
                    OverrideAvailability overrideAvailability = new OverrideAvailability();
                    overrideAvailability.setDate(startTime1.toLocalDate());
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1.toLocalTime(), endTime1.toLocalTime());
                    overrideAvailability.setSlotIds(listOfSlots);
                    overrideAvailability.setScheduleId(scheduleId);
                    overrideAvailabilityRepository.save(overrideAvailability);

                }
                else{
                    Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1.toLocalTime(), endTime1.toLocalTime());
                    overrideAvailability1.setSlotIds(listOfSlots);
                    overrideAvailabilityRepository.save(overrideAvailability1);

                }
            }
            else{
                OverrideAvailability overrideAvailability1 = overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId,startTime1.toLocalDate());
                Set<Long> slotIdsAns = overrideAvailability1.getSlotIds();
                Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1.toLocalTime(), endTime1.toLocalTime());
                Set<Long>ans=new HashSet<>();
                ans.addAll(listOfSlots);
                ans.addAll(slotIdsAns);
                overrideAvailability1.setSlotIds(ans);
                overrideAvailabilityRepository.save(overrideAvailability1);





            }

//            Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1.toLocalTime(), endTime1.toLocalTime());
//            ansListToStoreFinalAnswer.add(listOfSlots);
//            LocalDate date = addOverrideAvailabilityRequest.get(i).getStartTimeSlot2().toLocalDate();
//            List<Long> ans = new ArrayList<>();
//
//                OverrideAvailability overrideAvailability1 = overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId, date);
//
//            if (overrideAvailability1 == null) {
//                OverrideAvailability overrideAvailability = new OverrideAvailability();
//                overrideAvailability.setDate(date);
//                overrideAvailability.setSlotIds(listOfSlots);
//                overrideAvailability.setScheduleId(scheduleId);
//                overrideAvailabilityRepository.save(overrideAvailability);
//            } else {
////                Set<Long> slotIdsAns = overrideAvailability1.getSlotIds();
////                slotIdsAns.addAll(listOfSlots);
//
//                overrideAvailability1.setSlotIds(listOfSlots);
//                overrideAvailabilityRepository.save(overrideAvailability1);



        }
        List<OverrideAvailability> overrideAvailability = overrideAvailabilityRepository.findByScheduleId(scheduleId);
       AddCOmbinedAvailabilityAndUnavailabilityResponse ansList = OverrideAvailabilityMapper.convertEntityToDTO1(overrideAvailability);


        return new ResponseEntity<>
                (new AddOverrideAvailabilityRespone
                        ("1",
                                "The override have been updatesd", ansList), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<AddOverrideAvailabilityRespone> GetOverrideAvailability(UUID scheduleId) {
        if (scheduleId == null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        List<OverrideAvailability> overrideAvailabilities = overrideAvailabilityRepository.findByScheduleId(scheduleId);
        AddCOmbinedAvailabilityAndUnavailabilityResponse ansList = OverrideAvailabilityMapper.convertEntityToDTO1(overrideAvailabilities);


        return new ResponseEntity<>
                (new AddOverrideAvailabilityRespone
                        ("1",
                                "The override list is for the scheduleId:" + scheduleId, ansList), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<AddOverrideAvailabilityRespone>DeleteOverrideAvailability(UUID scheduleId,DeleteOverrideRequest deleteOverrideRequest){
        if (scheduleId == null ||  deleteOverrideRequest==null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        OverrideAvailability overrideAvailability=overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId,deleteOverrideRequest.getStartTime().toLocalDate());
        Set<Long> listOfSlots= AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(deleteOverrideRequest.getStartTime().toLocalTime(),deleteOverrideRequest.getEndTime().toLocalTime());
                Set<Long>list=overrideAvailability.getSlotIds();
                //overrideAvailability.setSlotIds();
        list.removeAll(listOfSlots);
        overrideAvailability.setSlotIds(list);
        overrideAvailabilityRepository.save(overrideAvailability);

        return new ResponseEntity<>
                (new AddOverrideAvailabilityRespone
                        ("1",
                                "The override is deleted:" ,null), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<AddOverrideAvailabilityRespone>DeleteOverrideUnAvailabilitys(UUID scheduleId,DeleteOverrideUnavailabilityRequest deleteOverrideUnavailability){
        if (scheduleId == null || deleteOverrideUnavailability  ==null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        OverrideAvailability overrideAvailability=overrideAvailabilityRepository.findByScheduleIdAndDate(scheduleId,deleteOverrideUnavailability.getDate());
        Set<Long>slot=overrideAvailability.getSlotIds();
        slot.clear();
        overrideAvailability.setSlotIds(slot);
        overrideAvailabilityRepository.save(overrideAvailability);
        return new ResponseEntity<>
                (new AddOverrideAvailabilityRespone
                        ("1",
                                "The overrideUnavailability is deleted:" ,null), HttpStatus.OK);

    }








}
