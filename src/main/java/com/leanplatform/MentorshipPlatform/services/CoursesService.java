package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCourseRequest;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CoursesService {
    ResponseEntity<AddCoursesResponse> createCourse(UUID userId, AddCourseRequest addCourseRequest);
}
