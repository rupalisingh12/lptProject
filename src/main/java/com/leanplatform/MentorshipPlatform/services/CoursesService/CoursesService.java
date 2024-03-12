package com.leanplatform.MentorshipPlatform.services.CoursesService;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCourseRequest;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.ExtraDeatilsRequest;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.ExtraDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CoursesService {
    ResponseEntity<AddCoursesResponse> createCourse(String userName, AddCourseRequest addCourseRequest);
//    ResponseEntity<AddCoursesResponse> getCousreOfMentor(String userName);
     ResponseEntity<AddCoursesResponse>updateCourse( UUID courseId,AddCourseRequest addCourseRequest);
    ResponseEntity<AddCoursesResponse>getCourses(String userName);
    ResponseEntity<AddCoursesResponse>enableOrDisableCourse(UUID courseId,AddCourseRequest addCourseRequest);
   ResponseEntity<ExtraDetailsResponse>addExtraDetailsOfCourse(UUID courseId , ExtraDeatilsRequest extraDeatilsRequest );

    ResponseEntity<ExtraDetailsResponse>getExtraDetails(UUID courseId);

}
