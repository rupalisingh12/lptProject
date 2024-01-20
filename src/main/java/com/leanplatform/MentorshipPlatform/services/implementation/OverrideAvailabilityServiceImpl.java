package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityRespone;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.DeleteOverrideRequest;
import com.leanplatform.MentorshipPlatform.entities.OverrideAvailability;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityV2Mapper;
import com.leanplatform.MentorshipPlatform.mappers.OverrideAvailabilityMapper;
import com.leanplatform.MentorshipPlatform.mappers.Slot;
import com.leanplatform.MentorshipPlatform.mappers.Slot2;
import com.leanplatform.MentorshipPlatform.repositories.OverrideAvailabilityRepository;
import com.leanplatform.MentorshipPlatform.services.OverrideAvailabilityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public ResponseEntity<AddOverrideAvailabilityRespone> UpdateOverrideAvailability(UUID scheduleId, List<AddOverrideAvailabilityRequest> addOverrideAvailabilityRequest) {
        if (scheduleId == null || addOverrideAvailabilityRequest == null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < addOverrideAvailabilityRequest.size(); i++) {
            LocalDateTime startTime1 = addOverrideAvailabilityRequest.get(i).getStartTimeSlot2();
            LocalDateTime endTime1 = addOverrideAvailabilityRequest.get(i).getEndTimeSlot2();
            Set<Long> listOfSlots = AvailabilityV2Mapper.convertStartTimeEndTimeIntoSlotIds(startTime1.toLocalTime(), endTime1.toLocalTime());
            LocalDate date = addOverrideAvailabilityRequest.get(i).getStartTimeSlot2().toLocalDate();
            List<Long> ans = new ArrayList<>();
            OverrideAvailability overrideAvailability1 = overrideAvailabilityRepository.findByDate(date);
            if (overrideAvailability1 == null) {
                OverrideAvailability overrideAvailability = new OverrideAvailability();
                overrideAvailability.setDate(date);
                overrideAvailability.setSlotIds(listOfSlots);
                overrideAvailability.setScheduleId(scheduleId);
                overrideAvailabilityRepository.save(overrideAvailability);
            } else {
//                Set<Long> slotIdsAns = overrideAvailability1.getSlotIds();
//                slotIdsAns.addAll(listOfSlots);
                overrideAvailability1.setSlotIds(listOfSlots);
                overrideAvailabilityRepository.save(overrideAvailability1);

            }

        }
        List<OverrideAvailability> overrideAvailability = overrideAvailabilityRepository.findByScheduleId(scheduleId);
        List<AddOverrideAvailabilityResponseDTO> ansList = OverrideAvailabilityMapper.convertEntityToDTO1(overrideAvailability);


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
        List<AddOverrideAvailabilityResponseDTO> ansList = OverrideAvailabilityMapper.convertEntityToDTO1(overrideAvailabilities);


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







}
