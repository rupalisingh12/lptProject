package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityNewMapper {
    public static CreateAvailabilityNewResponseDTO convertDtoToEntity(AvailabilityNew availabilityNew){
         CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO=new CreateAvailabilityNewResponseDTO();
         createAvailabilityNewResponseDTO.setAvailabilityId(availabilityNew.getAvailabilityId());
         createAvailabilityNewResponseDTO.setScheduleId(availabilityNew.getScheduleId());


         createAvailabilityNewResponseDTO.setSlotIds(availabilityNew.getSlotIds());

         createAvailabilityNewResponseDTO.setDays(availabilityNew.getDay());


        // createAvailabilityNewResponseDTO.setDays(availabilityNew.getDays());
         return createAvailabilityNewResponseDTO;

    }
    public  static List<Long> convertStartTimeEndTimeIntoSlots(LocalDateTime startTime ,LocalDateTime endTime) {
        int numberOfSlots = 24 * 2;
        String[] timeSlotsArray = new String[numberOfSlots];

        // Populate the array with time slots
        for (long i = 0; i < numberOfSlots; i++) {
            long startHour = i / 2;
            long startMinute = (i % 2) * 30;
            long endHour = (startHour + (startMinute + 30) / 60) % 24;
            long endMinute = (startMinute + 30) % 60;

            String startTime1 = String.format("%02d:%02d", startHour, startMinute);
            String endTime1 = String.format("%02d:%02d", endHour, endMinute);
            timeSlotsArray[(int) i] = startTime1 + " - " + endTime1;
        }



        //  Get slots for a specific time range


        List<Long> slotIds1 = getSlotIdsForTimeRange(timeSlotsArray, startTime, endTime);
       System.out.println("Slot IDs for the time range (" + startTime + " - " + endTime + "): " + slotIds1);
          return slotIds1;

    }


    private static List<Long> getSlotIdsForTimeRange(String[] timeSlotsArray, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int startSlot = getSlotIdForLocalDateTime(timeSlotsArray, startDateTime);
        int endSlot = getSlotIdForLocalDateTime(timeSlotsArray, endDateTime);

        List<Long> slotIds = getSlotIdsForRange(timeSlotsArray, startSlot, endSlot);
        return slotIds;
    }

    // Function to get the slot IDs for a specific range
    // Function to get the slot IDs for a specific range
    private static List<Long> getSlotIdsForRange(String[] timeSlotsArray, int startSlot, int endSlot) {
        List<Long> slotIds = new ArrayList<>();
        for (long i = startSlot; i < endSlot; i++) {
            slotIds.add(i);
        }
        return slotIds;
    }


    // Function to get the slot ID for a specific LocalDateTime
    private static int getSlotIdForLocalDateTime(String[] timeSlotsArray, LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();

        for (int i = 0; i < timeSlotsArray.length; i++) {
            String[] slotParts = timeSlotsArray[i].split(" - ");
            int startHour = Integer.parseInt(slotParts[0].split(":")[0]);
            int startMinute = Integer.parseInt(slotParts[0].split(":")[1]);
            int endHour = Integer.parseInt(slotParts[1].split(":")[0]);
            int endMinute = Integer.parseInt(slotParts[1].split(":")[1]);

            boolean isInRange = (hour > startHour || (hour == startHour && minute >= startMinute)) &&
                    (hour < endHour || (hour == endHour && minute < endMinute));

            LocalDateTime endDateTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(endHour, endMinute));

            if (isInRange && localDateTime.isBefore(endDateTime)) {
                return i + 1; // Slot IDs are 1-based
            }
        }

        return -1;

    }


}
