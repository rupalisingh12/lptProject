package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExtraDetailsResponseDTO {
    private String instructorName;
    private Double price;
    private String description;
    private Double discount;
    private String overview;
    private List<String> whoThisCourseIsFor;
    private String fileUrls;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<String>thisCourseIncludes;

    private Duration duration;
    private Double totalNoOfSeats;
    private Double noOfSeatsLeft;

}
