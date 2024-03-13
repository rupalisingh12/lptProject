package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseCombinedDTO {
    private UUID courseId;
    private String name;
    private Double price;
    private String description;
    private Double discount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String courseStatus;
    private String fileUrls;
    private Duration duration;
    private String instructorName;
    private Double totalNoOfSeats;
    private Double noOfSeatsLeft;
    private String overview;
    private List<String> whoThisCourseIsFor;





}
