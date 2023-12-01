package com.leanplatform.MentorshipPlatform.dto.SessionController;

import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionDetailsDto {
    private String mentorName;
    private String menteeName;
    private String serviceOffered;
    private DaysOfTheWeek dayOfTheWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String sessionSummary;
    private String reasonForSession;
    private String sessionExpectations;
    private Double sessionRating;
    private String feedback;
}
