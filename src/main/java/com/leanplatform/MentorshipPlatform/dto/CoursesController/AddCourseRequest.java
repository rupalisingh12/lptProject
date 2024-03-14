package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
    //look a this functionality
    private String courseStatus;
    private Double discount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String courseName;
    private MultipartFile image;
    //do not add
    private LocalDateTime duration;
    private Double totalNoOfSeats;
    //do not add
    private Double noOfSeatsLeft;
    private Boolean isEnabled;
}