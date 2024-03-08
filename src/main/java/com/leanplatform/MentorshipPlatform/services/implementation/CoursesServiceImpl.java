package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCourseRequest;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.Courses;
import com.leanplatform.MentorshipPlatform.mappers.CoursesMapper;
import com.leanplatform.MentorshipPlatform.repositories.CoursesRepository;
import com.leanplatform.MentorshipPlatform.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    CoursesRepository coursesRepository;
    @Override
    public ResponseEntity<AddCoursesResponse> createCourse(String userName, AddCourseRequest addCourseRequest){
        if(userName==null || addCourseRequest==null){
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "Null request recieved ", null
                    ), HttpStatus.BAD_REQUEST);
        }
        Courses courses=new Courses();
        courses.setDiscount(addCourseRequest.getDiscount());
        courses.setName(addCourseRequest.getName());
        courses.setPrice(addCourseRequest.getPrice());
        courses.setDescription(addCourseRequest.getDescription());
        courses.setStartDateTime(addCourseRequest.getStartDateTime());
        courses.setEndDateTime(addCourseRequest.getEndDateTime());
       Duration duration= Duration.between(courses.getStartDateTime() ,courses.getEndDateTime());
        courses.setDuration(duration);
        courses.setTotalNoOfSeats(addCourseRequest.getTotalNoOfSeats());
        courses.setNoOfSeatsLeft(addCourseRequest.getNoOfSeatsLeft());
        coursesRepository.save(courses);
        return new ResponseEntity<>(new AddCoursesResponse("1","The courses details has been saved ",null),HttpStatus.CREATED);

    }
//    @Override
//    public ResponseEntity<AddCoursesResponse> getCousreOfMentor(String userName) {
//        if (userName == null) {
//            return new ResponseEntity<>(new AddCoursesResponse
//                    ("0",
//                            "Null request recieved ", null
//                    ), HttpStatus.BAD_REQUEST);
//
//        }
//        Courses course = coursesRepository.findByUserName(userName);
//        if (course != null) {
//            AddCoursesResponseDTO addCoursesResponseDTO = CoursesMapper.convertEntityToDto(course);
//
//            return new ResponseEntity<>(new AddCoursesResponse
//                    ("0",
//                            "The course is ", addCoursesResponseDTO
//                    ), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(new AddCoursesResponse
//                    ("0",
//                            "The course is ", null
//                    ), HttpStatus.BAD_REQUEST);
//        }






}
