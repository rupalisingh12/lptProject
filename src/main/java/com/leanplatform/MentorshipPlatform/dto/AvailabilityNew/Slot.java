package com.leanplatform.MentorshipPlatform.dto.AvailabilityNew;

import lombok.*;

import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Data
public class Slot {
    private Long slotId;
    private LocalTime startTime;
    private LocalTime endTime;



}
