package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCourseRequest;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import com.leanplatform.MentorshipPlatform.entities.Courses;
import com.leanplatform.MentorshipPlatform.repositories.CoursesRepository;
import com.leanplatform.MentorshipPlatform.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    CoursesRepository coursesRepository;
    @Override
    public ResponseEntity<AddCoursesResponse> createCourse(UUID userId, AddCourseRequest addCourseRequest){
        if(userId==null || addCourseRequest==null){
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
        coursesRepository.save(courses);
        return new ResponseEntity<>(new AddCoursesResponse("1","The courses details has been saved ",null),HttpStatus.CREATED);

    }

}
