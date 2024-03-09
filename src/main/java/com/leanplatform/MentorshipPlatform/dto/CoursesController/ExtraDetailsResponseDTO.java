package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExtraDetailsResponseDTO {
    private String about;
    private String field1;
    private String field2;
    private String field3;
    private String instructorName;
    private Double price;
    private String description;
    private Double discount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;
    private Double totalNoOfSeats;
    private Double noOfSeatsLeft;

}
