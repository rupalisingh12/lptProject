package com.leanplatform.MentorshipPlatform.mappers;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotMapper {
    public static List<ArrayList<Integer>> getAllTimeSlots(List<ArrayList<String>> timeIntervals) {
        int slotsPerHour = 2; // There are 2 half-hour slots per hour
        List<ArrayList<Integer>> allTimeSlots = new ArrayList<>();

        for (ArrayList<String> interval : timeIntervals) {
            ArrayList<Integer> timeSlots = new ArrayList<>();
            if (interval.size() != 2) {
                // Each interval should contain exactly two strings (start time and end time)
                continue;
            }


            // Parse the start and end times for the interval
            String startTimeStr = interval.get(0);
            String endTimeStr = interval.get(1);

            String[] startParts = startTimeStr.split(":");
            int startHour = Integer.parseInt(startParts[0]);
            int startMinute = Integer.parseInt(startParts[1]);

            String[] endParts = endTimeStr.split(":");
            int endHour = Integer.parseInt(endParts[0]);
            int endMinute = Integer.parseInt(endParts[1]);

            // Calculate the slot numbers for the specified interval
            int startSlot = (startHour * slotsPerHour) + (startMinute / 30) + 1;
            int endSlot = (endHour * slotsPerHour) + (endMinute / 30);

            // Populate the timeSlots list
            for (int slot = startSlot; slot <= endSlot; slot++) {
                timeSlots.add(slot);
            }

            allTimeSlots.add(timeSlots);
        }

        return allTimeSlots;
    }

}
