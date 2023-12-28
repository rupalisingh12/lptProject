package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.Slot;
import com.leanplatform.MentorshipPlatform.services.SlotService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    private final List<Slot> allSlots = new ArrayList<>();

    public SlotServiceImpl() {
        LocalTime currentStartTime = LocalTime.of(0, 0, 0);
        LocalTime currentEndTime = LocalTime.of(0, 30, 0);
        for (long i = 1; i <= 48; i++) {
            if(i==48){
                      Slot slot=new Slot((long)48,LocalTime.of(23, 30, 0),LocalTime.of(24, 0, 0));
            }
            else {

                Slot slot = new Slot(i, currentStartTime, currentEndTime);
                currentStartTime.plusMinutes(30);
                currentEndTime.plusMinutes(30);
                allSlots.add(slot);
            }
        }
    }

    @Override
    public List<Slot> findSlotIdsByTimeRange(LocalTime startTime1, LocalTime endTime1) {

        List<Slot> arr=new ArrayList<>();
        for(int i=0;i<allSlots.size();i++){
            Slot slot=allSlots.get(i);
            if(slot.getStartTime().isAfter(startTime1) || slot.getStartTime().equals(startTime1) && slot.getEndTime().isBefore(endTime1) ||slot.getStartTime().equals(endTime1)){
                arr.add(slot);
            }
        }
        return arr;




    }
}
