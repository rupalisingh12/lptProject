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
    //what if a mentor tries to save the same course( with all the deatils same ) again
    // what should happen, should it geted saved or what do we expect
    @PostMapping("/addCourses")
    public ResponseEntity<AddCoursesResponse> addNewCourses(@RequestParam("userName") String userName, @RequestBody AddCourseRequest addCourseRequest) {
        if (userName == null || addCourseRequest == null) {
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "Null request recieved ", null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return coursesService.createCourse(userName, addCourseRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0","Caught in the catch block"+e.getLocalizedMessage()
                            , null
                    ), HttpStatus.BAD_REQUEST);

        }
    }
    //add instructorName in list of of courses
    //do not make this api
//    @GetMapping("/getCourse/{courseId}")
//    public ResponseEntity<AddCoursesResponse>fetchAcourse(@RequestParam("userName")String userName){
//        if(userName==null){
//            return new ResponseEntity<>(new AddCoursesResponse
//                    ("0",
//                            "Null request recieved ", null
//                    ), HttpStatus.BAD_REQUEST);
//        }
//        try{
//           return coursesService.getCousreOfMentor(userName);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(new AddCoursesResponse
//                    ("0","Caught in the catch block"+e.getLocalizedMessage()
//                            , null
//                    ), HttpStatus.BAD_REQUEST);
//        }
//    }


}
