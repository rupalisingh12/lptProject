package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.*;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.Courses;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.ExtraDetailsOfCourses;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.CourseFeatureMapper.CoursesMapper;
import com.leanplatform.MentorshipPlatform.repositories.CoursesFeatureRepository.CoursesRepository;
import com.leanplatform.MentorshipPlatform.repositories.CoursesFeatureRepository.ExtraDetailsCOursesRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.services.CoursesService.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    CoursesRepository coursesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExtraDetailsCOursesRepository extraDetailsCOursesRepository;

    @Override
    public ResponseEntity<AddCoursesResponse> createCourse(String userName, AddCourseRequest addCourseRequest){
        if(userName==null || addCourseRequest==null || addCourseRequest.getName()==null ||
                addCourseRequest.getPrice()==null || addCourseRequest.getDescription()==null ||
                addCourseRequest.getStartDateTime()==null  ||addCourseRequest.getEndDateTime()==null ||
                addCourseRequest.getTotalNoOfSeats()==null){
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
        courses.setIsEnabled(addCourseRequest.getIsEnabled());
        courses.setNoOfSeatsLeft(addCourseRequest.getNoOfSeatsLeft());
        UserEntity userEntity=userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "Null request recieved ", null
                    ), HttpStatus.BAD_REQUEST);
        }
        courses.setUserId(userEntity.getUserId());
        courses.setUserName(userEntity.getUserName());
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

   public ResponseEntity<AddCoursesResponse>updateCourse( UUID courseId , AddCourseRequest addCourseRequest){
       if(courseId==null || addCourseRequest==null){
           return new ResponseEntity<>(new AddCoursesResponse("0","Null request reciieved",null),HttpStatus.BAD_REQUEST);
       }
      Courses courses= coursesRepository.findByCourseId(courseId);
       if(addCourseRequest.getPrice()!=null){
           courses.setPrice(addCourseRequest.getPrice());
       }
       if(addCourseRequest.getDiscount()!=null){
           courses.setDiscount(addCourseRequest.getDiscount());
       }
       if(addCourseRequest.getIsEnabled()!=null){
           courses.setIsEnabled(addCourseRequest.getIsEnabled());
       }
       coursesRepository.save(courses);
       return new ResponseEntity<>(
               new AddCoursesResponse(
                       "1","The courses details has been updated ",null)
               ,HttpStatus.OK);


   }
   public ResponseEntity<AddCoursesResponse>getCourses(String userName){
        if(userName==null){
            return new ResponseEntity<>(new AddCoursesResponse("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);

        }
       List<Courses> courses= coursesRepository.findByUserName(userName);
        List<AddCoursesResponseDTO>ans=new ArrayList<>();
        for(int i=0;i<courses.size();i++){
            AddCoursesResponseDTO addCoursesResponseDTO=  CoursesMapper.convertEntityToDto(courses.get(i));
            ans.add(addCoursesResponseDTO);

        }
       return new ResponseEntity<>(
               new AddCoursesResponse(
                       "1","The List of the courses are  ",ans)
               ,HttpStatus.OK);






   }
   @Override
   public ResponseEntity<AddCoursesResponse>enableOrDisableCourse(UUID courseId,AddCourseRequest addCourseRequest){
       if(courseId==null){
           return new ResponseEntity<>(new AddCoursesResponse("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);

       }
       Courses courses =coursesRepository.findByCourseId(courseId);
       if(courses!=null){
           courses.setIsEnabled(addCourseRequest.getIsEnabled());
           coursesRepository.save(courses);

       }
       return new ResponseEntity<>(
               new AddCoursesResponse(
                       "1","The course enabledOrdisabled is updated  ",null)
               ,HttpStatus.OK);





    }
    @Override
     public ResponseEntity<ExtraDetailsResponse>addExtraDetailsOfCourse(UUID courseId, ExtraDeatilsRequest extraDeatilsRequest ) {
        if (courseId == null) {
            return new ResponseEntity<>
                    (new ExtraDetailsResponse
                            ("0", "Null request reciieved", null),
                            HttpStatus.BAD_REQUEST);


        }
       ExtraDetailsOfCourses extraDetailsOfCourses1= extraDetailsCOursesRepository.findByCourseId(courseId);
        if(extraDetailsOfCourses1!=null){
            return new ResponseEntity<>
                    (new ExtraDetailsResponse
                            ("0", "Extra details already exist for this course", null),
                            HttpStatus.BAD_REQUEST);
        }
        ExtraDetailsOfCourses extraDetailsOfCourses = new ExtraDetailsOfCourses();
        extraDetailsOfCourses.setAbout(extraDeatilsRequest.getAbout());
        extraDetailsOfCourses.setField1(extraDeatilsRequest.getField1());

        extraDetailsOfCourses.setCourseId(courseId);
        extraDetailsOfCourses.setField2(extraDeatilsRequest.getField2());
        extraDetailsOfCourses.setField3(extraDeatilsRequest.getField3());

        extraDetailsCOursesRepository.save(extraDetailsOfCourses);
        return new ResponseEntity<>(
                new ExtraDetailsResponse(
                        "1", "The course enabledOrdisabled is updated  ", null)
                , HttpStatus.OK);

    }
    @Override



    public ResponseEntity<ExtraDetailsResponse>getExtraDetails(UUID courseId){
        if(courseId==null){
            return new ResponseEntity<>(
                    new ExtraDetailsResponse (
                            "0", "Null request reciieved", null),
                    HttpStatus.BAD_REQUEST);

        }
        ExtraDetailsOfCourses extraDetailsOfCourses= extraDetailsCOursesRepository.findByCourseId(courseId);
        Courses courses= coursesRepository.findByCourseId(courseId);
        ExtraDetailsResponseDTO extraDetailsResponseDTO= CoursesMapper.convertEntityToDTO1(courses ,extraDetailsOfCourses);
        return new ResponseEntity<>(
                new ExtraDetailsResponse(
                        "1", "The course enabledOrdisabled is updated  ",extraDetailsResponseDTO )
                , HttpStatus.OK);

    }






}
