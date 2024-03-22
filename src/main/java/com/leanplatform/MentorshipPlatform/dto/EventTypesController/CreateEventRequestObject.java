package com.leanplatform.MentorshipPlatform.dto.EventTypesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEventRequestObject {
    private Integer length;
    private String title;
    private Double price;
    private String description;
    private Long noOfStudents;
   // private Double price;

}
