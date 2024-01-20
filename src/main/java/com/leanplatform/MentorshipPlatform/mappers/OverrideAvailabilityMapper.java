package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.OverrideAvailability;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.leanplatform.MentorshipPlatform.mappers.AvailabilityV2Mapper.catchSlotIdsListAndConvertIntoStartTimeEndTime;

public class OverrideAvailabilityMapper {




//    public static List<AddOverrideAvailabilityResponseDTO> convertEntityToDTO(OverrideAvailability overrideAvailability){
//        List<AddOverrideAvailabilityResponseDTO> addOverrideAvailabilityResponseDTO=new ArrayList<>();
//        Set<Long> ans= overrideAvailability.getSlotIds();
//        List<Slot> ans1=catchSlotIdsListAndConvertIntoStartTimeEndTime(ans);
//        for(int i=0;i<ans1.size();i++) {
//            AddOverrideAvailabilityResponseDTO addOverrideAvailabilityResponseDTO1=new AddOverrideAvailabilityResponseDTO();
//
//            addOverrideAvailabilityResponseDTO1.setSlot(ans1.get(i));
//            addOverrideAvailabilityResponseDTO.add(addOverrideAvailabilityResponseDTO1);
//        }
//        return addOverrideAvailabilityResponseDTO;
//    }
    public static List<AddOverrideAvailabilityResponseDTO> convertEntityToDTO1(List<OverrideAvailability> overrideAvailability){
        List<AddOverrideAvailabilityResponseDTO> addOverrideAvailabilityResponseDTO=new ArrayList<>();
        for(int i=0;i<overrideAvailability.size();i++) {
            OverrideAvailability overrideAvailability1 = overrideAvailability.get(i);
            List<Slot> ans = catchSlotIdsListAndConvertIntoStartTimeEndTime(overrideAvailability1.getSlotIds());
            LocalDate date= overrideAvailability1.getDate();
            for(int j=0;j<ans.size();j++){
                Slot slot=ans.get(j);
               LocalTime time1= slot.getStartTime();
                LocalDateTime combinedDateTime = date.atTime(time1);
                    LocalTime time2=slot.getEndTime();
                LocalDateTime combinedDateTime2=date.atTime(time2);
                AddOverrideAvailabilityResponseDTO addOverrideAvailabilityResponseDTO1=new AddOverrideAvailabilityResponseDTO();
              addOverrideAvailabilityResponseDTO1.setStartTime(combinedDateTime);
              addOverrideAvailabilityResponseDTO1.setEndTime(combinedDateTime2);
              addOverrideAvailabilityResponseDTO.add(addOverrideAvailabilityResponseDTO1);
            // addOverrideAvailabilityResponseDTO.add(combinedDateTime);

            }
         // LocalDate date= overrideAvailability1.getDate();
        }
        return addOverrideAvailabilityResponseDTO;

    }
}
