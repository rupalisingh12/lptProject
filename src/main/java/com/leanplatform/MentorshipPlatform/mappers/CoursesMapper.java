package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.ExtraDetailsResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.Courses;
import com.leanplatform.MentorshipPlatform.entities.ExtraDetailsOfCourses;

import java.util.UUID;

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
        addCoursesResponseDTO.setCourseId(course.getCourseId());
        addCoursesResponseDTO.setTotalNoOfSeats(course.getTotalNoOfSeats());
        addCoursesResponseDTO.setNoOfSeatsLeft(course.getNoOfSeatsLeft());
        addCoursesResponseDTO.setDuration(course.getDuration());
        return addCoursesResponseDTO;

    }
    public static ExtraDetailsResponseDTO convertEntityToDTO1(Courses courses , ExtraDetailsOfCourses extraDetailsOfCourses){
        ExtraDetailsResponseDTO extraDetailsResponseDTO=new ExtraDetailsResponseDTO();
        extraDetailsResponseDTO.setAbout(extraDetailsOfCourses.getAbout());
        extraDetailsResponseDTO.setField1(extraDetailsOfCourses.getField1());
        extraDetailsResponseDTO.setField2(extraDetailsOfCourses.getField2());
        extraDetailsResponseDTO.setField3(extraDetailsOfCourses.getField3());
        extraDetailsResponseDTO.setInstructorName(courses.getName());
        extraDetailsResponseDTO.setDiscount(courses.getDiscount());
        extraDetailsResponseDTO.setDescription(courses.getDescription());
        extraDetailsResponseDTO.setPrice(courses.getPrice());
        extraDetailsResponseDTO.setDuration(courses.getDuration());
        extraDetailsResponseDTO.setStartDateTime(courses.getStartDateTime());
        extraDetailsResponseDTO.setEndDateTime(courses.getEndDateTime());
        extraDetailsResponseDTO.setTotalNoOfSeats(courses.getTotalNoOfSeats());
        extraDetailsResponseDTO.setNoOfSeatsLeft(courses.getNoOfSeatsLeft());
        return  extraDetailsResponseDTO;

    }

}
