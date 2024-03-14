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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    CoursesRepository coursesRepository;
    @Autowired
    UserRepository userRepository;
    @Value("${s3.bucket.baseUrl}")
    String s3BaseUrl;
    @Value("${aws.bucketName}")
    private String bucketName;
    @Autowired
    S3Client s3Client;
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
       UserEntity user= userRepository.findByUserName(userName);
        if(user==null){
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "The user does not exist in the db ", null
                    ), HttpStatus.BAD_REQUEST);
        }
        Courses courses=new Courses();
        courses.setDiscount(addCourseRequest.getDiscount());
        courses.setName(addCourseRequest.getName());
        courses.setPrice(addCourseRequest.getPrice());
        courses.setDescription(addCourseRequest.getDescription());
        courses.setStartDateTime(addCourseRequest.getStartDateTime());
        courses.setEndDateTime(addCourseRequest.getEndDateTime());
        courses.setCourseStatus(addCourseRequest.getCourseStatus());
       Duration duration= Duration.between(courses.getStartDateTime() ,courses.getEndDateTime());
        courses.setDuration(duration);
        courses.setTotalNoOfSeats(addCourseRequest.getTotalNoOfSeats());
        try {
            String ans= savePosterInBucketAndCreateUrl(addCourseRequest.getImage());
            courses.setFileUrls(ans);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        courses.setIsEnabled(addCourseRequest.getIsEnabled());
        courses.setNoOfSeatsLeft(addCourseRequest.getNoOfSeatsLeft());
        courses.setUserId(user.getUserId());
        courses.setUserName(user.getUserName());
        coursesRepository.save(courses);
        return new ResponseEntity<>(new AddCoursesResponse("1","The courses details has been saved ",null),HttpStatus.CREATED);

    }
    public  String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            System.out.print(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        }
        return null;
    }
    public String savePosterInBucketAndCreateUrl(MultipartFile poster) throws IOException {
        if(poster!=null && !poster.isEmpty())
        {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String fileName = "Image-"  + currentDateTime +"." + getFileExtension(poster);
            InputStream inputStream=poster.getInputStream();
            PutObjectRequest request= PutObjectRequest.builder().bucket(bucketName).key(fileName).build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream,inputStream.available()));
            System.out.print(s3BaseUrl + "/" + fileName);
            return s3BaseUrl + "/" + fileName;
        }
        return null;
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
       //add a null check
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
       if(addCourseRequest.getStartDateTime()!=null){
           courses.setStartDateTime(addCourseRequest.getStartDateTime());
       }
       if(addCourseRequest.getEndDateTime()!=null){
           courses.setEndDateTime(addCourseRequest.getEndDateTime());
       }
       if(addCourseRequest.getCourseStatus()!=null){
           courses.setCourseStatus(addCourseRequest.getCourseStatus());
       }
       if(addCourseRequest.getTotalNoOfSeats()!=null){
           courses.setTotalNoOfSeats(addCourseRequest.getTotalNoOfSeats());
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
       UserEntity userEntity= userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<>(new AddCoursesResponse("0", "This user does not exist in the db", null), HttpStatus.BAD_REQUEST);

        }
       String name= userEntity.getName();
       List<Courses> courses= coursesRepository.findByUserName(userName);
        List<AddCoursesResponseDTO>ans=new ArrayList<>();
        for(int i=0;i<courses.size();i++){
            AddCoursesResponseDTO addCoursesResponseDTO=  CoursesMapper.convertEntityToDto(courses.get(i),name);
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
       //invalid,id check
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
        //invalid id course check
       ExtraDetailsOfCourses extraDetailsOfCourses1= extraDetailsCOursesRepository.findByCourseId(courseId);
        if(extraDetailsOfCourses1!=null){
            return new ResponseEntity<>
                    (new ExtraDetailsResponse
                            ("0", "Extra details already exist for this course", null),
                            HttpStatus.BAD_REQUEST);
        }
        ExtraDetailsOfCourses extraDetailsOfCourses = new ExtraDetailsOfCourses();
       // extraDetailsOfCourses.setAbout(extraDeatilsRequest.getAbout());
        extraDetailsOfCourses.setCourseId(courseId);
        extraDetailsOfCourses.setWhoThisCourseIsFor(extraDeatilsRequest.getWhoThisCourseIsFor());
        extraDetailsOfCourses.setOverview(extraDeatilsRequest.getOverview());
        extraDetailsOfCourses.setThisCourseIncludes(extraDeatilsRequest.getThisCourseIncludes());

       // extraDetailsOfCourses.setField2(extraDeatilsRequest.getField2());
       // extraDetailsOfCourses.setField3(extraDeatilsRequest.getField3());
       // extraDetailsOfCourses.setField6(extraDeatilsRequest.getField6());
       // extraDetailsOfCourses.setField5(extraDeatilsRequest.getField5());

        extraDetailsCOursesRepository.save(extraDetailsOfCourses);
        return new ResponseEntity<>(
                new ExtraDetailsResponse(
                        "1", "The extra details has been saved  ", null)
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

        //invalid id check
        ExtraDetailsOfCourses extraDetailsOfCourses= extraDetailsCOursesRepository.findByCourseId(courseId);
        Courses courses= coursesRepository.findByCourseId(courseId);
        String userName1=courses.getUserName();
        UserEntity userEntity=userRepository.findByUserName(userName1);
       String name1= userEntity.getName();
        ExtraDetailsResponseDTO extraDetailsResponseDTO= CoursesMapper.convertEntityToDTO1(courses ,extraDetailsOfCourses,name1);
        return new ResponseEntity<>(
                new ExtraDetailsResponse(
                        "1", "The course extra details are : ",extraDetailsResponseDTO )
                , HttpStatus.OK);

    }
    public ResponseEntity<DoMeetingResponse>meetingToAddCourse(String userName){
        if(userName==null){
            return new ResponseEntity<>(new DoMeetingResponse("0", "Null request reciieved", null), HttpStatus.BAD_REQUEST);

        }
        List<Courses> userName1= coursesRepository.findByUserName(userName);
        if(userName1.isEmpty()){
            DoMeetingResponseDTO doMeetingResponseDTO=new DoMeetingResponseDTO();
            doMeetingResponseDTO.setDoMeeting(true);


            return new ResponseEntity<>(new DoMeetingResponse  ("1", "The domeeting is", doMeetingResponseDTO), HttpStatus.BAD_REQUEST);

        }
    }






}
