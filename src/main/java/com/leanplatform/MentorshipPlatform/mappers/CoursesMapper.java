package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.Courses;

public class CoursesMapper {
    public static AddCoursesResponseDTO convertEntityToDto( Courses course){
        AddCoursesResponseDTO addCoursesResponseDTO =new AddCoursesResponseDTO();
        addCoursesResponseDTO.setName(course.getName());
        addCoursesResponseDTO.setDescription(course.getDescription());
        addCoursesResponseDTO.setDiscount(course.getDiscount());
        addCoursesResponseDTO.setTotalNoOfSeats(course.getTotalNoOfSeats());
        addCoursesResponseDTO.setStartDateTime(course.getStartDateTime());
        addCoursesResponseDTO.setEndDateTime(course.getEndDateTime());
        addCoursesResponseDTO.setPrice(course.getPrice());
        addCoursesResponseDTO.setTotalNoOfSeats(course.getTotalNoOfSeats());
        addCoursesResponseDTO.setNoOfSeatsLeft(course.getNoOfSeatsLeft());
        addCoursesResponseDTO.setDuration(course.getDuration());
        return addCoursesResponseDTO;

    }
}
