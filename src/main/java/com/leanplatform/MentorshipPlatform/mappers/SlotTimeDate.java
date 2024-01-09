package com.leanplatform.MentorshipPlatform.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class SlotTimeDate {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
