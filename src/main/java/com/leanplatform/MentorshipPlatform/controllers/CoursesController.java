package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.UpdateAvailabiliityNewResponse;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCourseRequest;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import com.leanplatform.MentorshipPlatform.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.http.HttpStatusCode;

import java.util.UUID;

@RestController
@RequestMapping("/Courses")
public class CoursesController {
    @Autowired
    CoursesService coursesService;
    @PostMapping("/addCourses")
    public ResponseEntity<AddCoursesResponse> addNewCourses(@RequestParam("userId") UUID userId, @RequestBody AddCourseRequest addCourseRequest) {
        if (userId == null || addCourseRequest == null) {
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "Null request recieved ", null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return coursesService.createCourse(userId, addCourseRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "Null request recieved ", null
                    ), HttpStatus.BAD_REQUEST);

        }
    }


}
