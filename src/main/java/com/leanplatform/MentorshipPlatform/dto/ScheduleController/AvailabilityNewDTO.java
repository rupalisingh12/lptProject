package com.leanplatform.MentorshipPlatform.dto.ScheduleController;

import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityNewDTO {
    private UUID availabilityId;
    private UUID scheduleId;
    private DaysOfTheWeek days;
    private List<TimeSlots> timeSlots;
}
