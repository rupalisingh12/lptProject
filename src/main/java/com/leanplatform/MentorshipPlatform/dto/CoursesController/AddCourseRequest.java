package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCourseRequest {
   // private UUID courseId;
    private String name;
    private Double price;
    private String description;
    private Double discount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime duration;
    private Double totalNoOfSeats;
    private Double noOfSeatsLeft;
    private Boolean isEnabled;
}