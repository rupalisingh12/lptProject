package com.leanplatform.MentorshipPlatform.services.CoursesService;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CoursesService {
    ResponseEntity<AddCoursesResponse> createCourse(String userName, AddCourseRequest addCourseRequest);
//    ResponseEntity<AddCoursesResponse> getCousreOfMentor(String userName);
     ResponseEntity<AddCoursesResponse>updateCourse( UUID courseId,UpdateCourseRequest updateCourseRequest);
    ResponseEntity<AddCoursesResponse>getCourses(String userName);
    ResponseEntity<AddCoursesResponse>enableOrDisableCourse(UUID courseId,AddCourseRequest addCourseRequest);
   ResponseEntity<ExtraDetailsResponse>addExtraDetailsOfCourse(UUID courseId , ExtraDeatilsRequest extraDeatilsRequest );

    ResponseEntity<ExtraDetailsResponse>getExtraDetails(UUID courseId);
   ResponseEntity<DoMeetingResponse>meetingToAddCourse(String userName);
    ResponseEntity<DoMeetingResponse>meetingToAddCourse1(String userName,DoMeetingRequest doMeetingRequest);

}
