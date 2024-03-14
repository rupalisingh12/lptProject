package com.leanplatform.MentorshipPlatform.mappers.CourseFeatureMapper;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.CourseResponseCombinedDTO;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.ExtraDetailsResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.Courses;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.ExtraDetailsOfCourses;

public class CoursesMapper {
    public static AddCoursesResponseDTO convertEntityToDto( Courses course,String name){
        AddCoursesResponseDTO addCoursesResponseDTO =new AddCoursesResponseDTO();
        addCoursesResponseDTO.setName(course.getName());
        addCoursesResponseDTO.setDescription(course.getDescription());
        addCoursesResponseDTO.setDiscount(course.getDiscount());
        addCoursesResponseDTO.setTotalNoOfSeats(course.getTotalNoOfSeats());
        addCoursesResponseDTO.setStartDateTime(course.getStartDateTime());
        addCoursesResponseDTO.setEndDateTime(course.getEndDateTime());
        addCoursesResponseDTO.setFileUrls(course.getFileUrls());
        addCoursesResponseDTO.setCourseStatus(course.getCourseStatus());
        addCoursesResponseDTO.setPrice(course.getPrice());
        addCoursesResponseDTO.setInstructorName(name);
        addCoursesResponseDTO.setCourseId(course.getCourseId());
        addCoursesResponseDTO.setTotalNoOfSeats(course.getTotalNoOfSeats());
        addCoursesResponseDTO.setNoOfSeatsLeft(course.getNoOfSeatsLeft());
        addCoursesResponseDTO.setDuration(course.getDuration());
        return addCoursesResponseDTO;

    }
    public static ExtraDetailsResponseDTO convertEntityToDTO1(Courses courses , ExtraDetailsOfCourses extraDetailsOfCourses,String name1){
        ExtraDetailsResponseDTO extraDetailsResponseDTO=new ExtraDetailsResponseDTO();
        extraDetailsResponseDTO.setDiscount(courses.getDiscount());
        extraDetailsResponseDTO.setDescription(courses.getDescription());
        extraDetailsResponseDTO.setName(courses.getName());
        extraDetailsResponseDTO.setWhoThisCourseIsFor(extraDetailsOfCourses.getWhoThisCourseIsFor());
        extraDetailsResponseDTO.setOverview(extraDetailsOfCourses.getOverview());
        extraDetailsResponseDTO.setPrice(courses.getPrice());
        extraDetailsResponseDTO.setDuration(courses.getDuration());
        extraDetailsResponseDTO.setStartDateTime(courses.getStartDateTime());
        extraDetailsResponseDTO.setInstructorName(name1);
        extraDetailsResponseDTO.setEndDateTime(courses.getEndDateTime());
        extraDetailsResponseDTO.setFileUrls(courses.getFileUrls());
        extraDetailsResponseDTO.setThisCourseIncludes(extraDetailsOfCourses.getThisCourseIncludes());
        extraDetailsResponseDTO.setTotalNoOfSeats(courses.getTotalNoOfSeats());
        extraDetailsResponseDTO.setNoOfSeatsLeft(courses.getNoOfSeatsLeft());
        return  extraDetailsResponseDTO;

    }


}
