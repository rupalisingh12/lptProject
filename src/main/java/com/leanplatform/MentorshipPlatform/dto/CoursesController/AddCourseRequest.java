package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class AddCourseRequest {
   // private UUID courseId;
    private String name;
    private Double price;
    private String description;
    private String courseStatus;
    private Double discount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String courseName;
    private MultipartFile fileUrls;
    private LocalDateTime duration;
    private Double totalNoOfSeats;
    private Double noOfSeatsLeft;
    private Boolean isEnabled;
}