package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class UpdateCourseRequest {
    private Double price;
    private Double discount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String courseStatus;
    private Double totalNoOfSeats;
}
