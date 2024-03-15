package com.leanplatform.MentorshipPlatform.mappers.CourseFeatureMapper;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.CourseResponseCombinedDTO;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.ExtraDetailsResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.Courses;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.ExtraDetailsOfCourses;

import java.time.LocalDateTime;

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
        LocalDateTime currentDateTime = LocalDateTime.now();
       if( currentDateTime.compareTo(course.getEndDateTime()) > 0) {
           addCoursesResponseDTO.setCourseStatus("inactive");
       }
       if(course.getIsEnabled()==false){
           addCoursesResponseDTO.setCourseStatus("disabled");
       }
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
        if(courses.getDiscount()!=null) {
            extraDetailsResponseDTO.setDiscount(courses.getDiscount());
        }
        else{
            extraDetailsResponseDTO.setDiscount(null);
        }
        if(courses.getDescription()!=null) {
            extraDetailsResponseDTO.setDescription(courses.getDescription());
        }
        else{
            extraDetailsResponseDTO.setDescription(null);
        }
        if(courses.getName()!=null) {
            extraDetailsResponseDTO.setName(courses.getName());
        }
        else{
            extraDetailsResponseDTO.setName(null);
        }
        if(extraDetailsOfCourses.getWhoThisCourseIsFor()!=null){
        extraDetailsResponseDTO.setWhoThisCourseIsFor(extraDetailsOfCourses.getWhoThisCourseIsFor());
        }
        else{
            extraDetailsResponseDTO.setWhoThisCourseIsFor(null);
        }
        if(extraDetailsOfCourses.getOverview()!=null) {
            extraDetailsResponseDTO.setOverview(extraDetailsOfCourses.getOverview());
        }
        else{
            extraDetailsResponseDTO.setOverview(null);
        }
        if(courses.getPrice()!=null) {
            extraDetailsResponseDTO.setPrice(courses.getPrice());
        }
        else{
            extraDetailsResponseDTO.setPrice(null);
        }
        if(courses.getDuration()!=null) {
            extraDetailsResponseDTO.setDuration(courses.getDuration());
        }
        else{
            extraDetailsResponseDTO.setDuration(null);
        }
        if(courses.getStartDateTime()!=null) {
            extraDetailsResponseDTO.setStartDateTime(courses.getStartDateTime());
        }
        else{
            extraDetailsResponseDTO.setStartDateTime(null);
        }
        if(name1!=null) {
            extraDetailsResponseDTO.setInstructorName(name1);
        }
        else{
            extraDetailsResponseDTO.setInstructorName(null);
        }
        if(courses.getEndDateTime()!=null) {
            extraDetailsResponseDTO.setEndDateTime(courses.getEndDateTime());
        }
        else{
            extraDetailsResponseDTO.setEndDateTime(null);
        }
        if(courses.getFileUrls()!=null) {
            extraDetailsResponseDTO.setFileUrls(courses.getFileUrls());
        }
        else{
            extraDetailsResponseDTO.setFileUrls(null);
        }
        if(extraDetailsOfCourses.getThisCourseIncludes()!=null) {
            extraDetailsResponseDTO.setThisCourseIncludes(extraDetailsOfCourses.getThisCourseIncludes());
        }
        else{
            extraDetailsResponseDTO.setThisCourseIncludes(null);
        }
        if(courses.getTotalNoOfSeats()!=null) {
            extraDetailsResponseDTO.setTotalNoOfSeats(courses.getTotalNoOfSeats());
        }
        else{
            extraDetailsResponseDTO.setTotalNoOfSeats(null);
        }
        if(courses.getTotalNoOfSeats()!=null) {
            extraDetailsResponseDTO.setNoOfSeatsLeft(courses.getNoOfSeatsLeft());
        }
        else{
            extraDetailsResponseDTO.setNoOfSeatsLeft(null);
        }
        return  extraDetailsResponseDTO;

    }


}
