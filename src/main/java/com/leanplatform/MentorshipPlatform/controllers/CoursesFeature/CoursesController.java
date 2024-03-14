package com.leanplatform.MentorshipPlatform.controllers.CoursesFeature;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.*;
import com.leanplatform.MentorshipPlatform.services.CoursesService.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Courses")
public class CoursesController {
    @Autowired
    CoursesService coursesService;
    //what if a mentor tries to save the same course( with all the deatils same ) again
    // what should happen, should it geted saved or what do we expect
    @PostMapping("/addCourses")
    public ResponseEntity<AddCoursesResponse> addNewCourses(@RequestParam("userName") String userName, @ModelAttribute AddCourseRequest addCourseRequest) {
        if (userName == null || addCourseRequest == null
                || addCourseRequest.getName()==null ||
                addCourseRequest.getPrice()==null || addCourseRequest.getDescription()==null ||
                addCourseRequest.getStartDateTime()==null  ||addCourseRequest.getEndDateTime()==null ||
                addCourseRequest.getTotalNoOfSeats()==null) {
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
    @PutMapping("/updateCourse")
    public ResponseEntity<AddCoursesResponse>UpdateCourse( @RequestParam("courseId")UUID courseId ,@RequestBody AddCourseRequest addCourseRequest) {
        if (courseId == null || addCourseRequest == null) {
            return new ResponseEntity<>(new AddCoursesResponse("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return coursesService.updateCourse(courseId, addCourseRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new AddCoursesResponse("0", "Caught in the catch block" + e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getCourses")
    public ResponseEntity<AddCoursesResponse>getCourse(@RequestParam("userName")String userName){
        if(userName==null){
            return new ResponseEntity<>(new AddCoursesResponse("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);

        }
        try{
           return coursesService.getCourses(userName);
        }
        catch  (Exception e) {
            return new ResponseEntity<>(new AddCoursesResponse("0", "Caught in the catch block" + e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }
    @PutMapping("/enableOrDisable")
    public ResponseEntity<AddCoursesResponse>updateENableOrDisabe(@RequestParam("courseId")UUID courseId,@RequestBody AddCourseRequest addCourseRequest){
        if(courseId==null){
            return new ResponseEntity<>(new AddCoursesResponse("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);

        }
        try{
           return coursesService.enableOrDisableCourse(courseId,addCourseRequest);
        }
        catch  (Exception e) {
            return new ResponseEntity<>(new AddCoursesResponse("0", "Caught in the catch block" + e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }
    //extraDeatils which will be shown on the website of the user
    //post api of extra details(nopt used from fronted , will only be used to put data manually in the db


    // this will be done on the website
    @PostMapping("/addExtraDetails")
    public ResponseEntity<ExtraDetailsResponse>addExtraDetails(@RequestParam("courseId")UUID courseId, @RequestBody ExtraDeatilsRequest extraDeatilsRequest ){
        if(courseId==null){
            return new ResponseEntity<>(new ExtraDetailsResponse ("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);

        }
        try{
           return coursesService.addExtraDetailsOfCourse(courseId,extraDeatilsRequest);
        }
        catch(Exception e){

                return new ResponseEntity<>
                        (new ExtraDetailsResponse
                                ("0", "Caught in the catch block" + e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

            }
        }

    @GetMapping("/getExtraDetails")
   public ResponseEntity<ExtraDetailsResponse>getExtraDetails(@RequestParam("courseId")UUID courseId){
    if(courseId==null){
        return new ResponseEntity<>(
                new ExtraDetailsResponse (
                        "0", "Null request reciieved", null),
                HttpStatus.BAD_REQUEST);

    }
    try{
        return coursesService.getExtraDetails(courseId);
    }
    catch
            (Exception e){

                return new ResponseEntity<>
                        (new ExtraDetailsResponse
                                ("0", "Caught in the catch block" + e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

            }
        }
    }










