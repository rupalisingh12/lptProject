package com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Slot {
    private LocalTime startTime;
    private LocalTime endTime;

}
