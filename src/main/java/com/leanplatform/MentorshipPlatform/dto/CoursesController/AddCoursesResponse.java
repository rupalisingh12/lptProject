package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCoursesResponse {
    private String statusCode;
    private String responseMessage;
    private List<AddCoursesResponseDTO> addCoursesResponseDTO;
    private DoMeetingResponseDTO doMeetingResponseDTO;



}
