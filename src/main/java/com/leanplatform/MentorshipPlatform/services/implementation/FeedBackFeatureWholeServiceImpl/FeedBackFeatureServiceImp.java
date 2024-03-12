package com.leanplatform.MentorshipPlatform.services.implementation.FeedBackFeatureWholeServiceImpl;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.*;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.*;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.FeadBackFeatureMapper.FeedBackFeatureMapper;
import com.leanplatform.MentorshipPlatform.mappers.FeadBackFeatureMapper.SuggestionMapper;
import com.leanplatform.MentorshipPlatform.repositories.FeedBackFeatureWholeRepository.*;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.services.FeedBackFeatureWholeService.FeedBackFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBackFeatureServiceImp implements  FeedBackFeatureService {

    @Autowired
    FeedBackFeatureRepository feedBackFeatureRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SuggestionRepository suggestionRepository;
    @Autowired
    FeedBackDetailsRepository feedBackDetailsRepository;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    ContactRepository contactRepository;
    @Value("${s3.bucket.baseUrl}")
    String s3BaseUrl;
    @Value("${aws.bucketName}")
    private String bucketName;
    @Autowired
    S3Client s3Client;
    @Autowired
    RatingRepository ratingRepository;


    @Override
    public ResponseEntity<GetAvailableButtonsResponse> AddAvailableButtons(String userName, AddAvailableButtonsRequest addAvailableButtonsRequest) {
        if (userName == null || addAvailableButtonsRequest == null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "Invalid Request", null),
                            HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = userRepository.findByUserName(userName);
        if (userEntity == null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "This user Does not exist in the db", null),
                            HttpStatus.NOT_FOUND);

        }
       List<FeedBackFeature> feedBackFeatureAns = feedBackFeatureRepository.findByUserName(userName);
        //1 feedBack
        if(addAvailableButtonsRequest.getFeedBack()==true){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "feedBack".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //have to make a new object
                FeedBackFeature feedBackFeature1=new FeedBackFeature();
                feedBackFeature1.setUserName(userName);
                feedBackFeature1.setDisableOrenabled(true);
                feedBackFeature1.setFormType("feedBack");
                feedBackFeatureRepository.save(feedBackFeature1);
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(true);
                feedBackFeatureRepository.save(feedbackFeature);

            }

        }
       else if(addAvailableButtonsRequest.getFeedBack()==false){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "feedBack".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //do not have to make a new object
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(false);
                feedBackFeatureRepository.save(feedbackFeature);
            }

        }
//        }
        //2 contact
        if(addAvailableButtonsRequest.getContact()==true){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "contact".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //have to make a new object
                FeedBackFeature feedBackFeature1=new FeedBackFeature();
                feedBackFeature1.setUserName(userName);
                feedBackFeature1.setDisableOrenabled(true);
                feedBackFeature1.setFormType("contact");
                feedBackFeatureRepository.save(feedBackFeature1);

            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(true);
                feedBackFeatureRepository.save(feedbackFeature);

            }

        }
        else if(addAvailableButtonsRequest.getContact()==false){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "contact".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //do not have to make a new object
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(false);
                feedBackFeatureRepository.save(feedbackFeature);


            }
        }
        //3 suggestion
        if(addAvailableButtonsRequest.getSuggestion()==true){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "suggestion".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //have to make a new object
                FeedBackFeature feedBackFeature1=new FeedBackFeature();
                feedBackFeature1.setUserName(userName);
                feedBackFeature1.setDisableOrenabled(true);
                feedBackFeature1.setFormType("suggestion");
                feedBackFeatureRepository.save(feedBackFeature1);
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(true);
                feedBackFeatureRepository.save(feedbackFeature);

            }
        }
        else if(addAvailableButtonsRequest.getSuggestion()==false){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "suggestion".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //do not have to make a new object
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(false);
                feedBackFeatureRepository.save(feedbackFeature);

            }
        }
        //4 issue
        if(addAvailableButtonsRequest.getIssue()==true){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "issue".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //have to make a new object
                FeedBackFeature feedBackFeature1=new FeedBackFeature();
                feedBackFeature1.setUserName(userName);
                feedBackFeature1.setDisableOrenabled(true);
                feedBackFeature1.setFormType("issue");
                feedBackFeatureRepository.save(feedBackFeature1);
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(true);
                feedBackFeatureRepository.save(feedbackFeature);


            }
        }

        else if(addAvailableButtonsRequest.getIssue()==false){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "issue".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //do not have to make a new object
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(false);
                feedBackFeatureRepository.save(feedbackFeature);





            }
        }// 5 rating
        if(addAvailableButtonsRequest.getRating()==true){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "rating".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //have to make a new object
                FeedBackFeature feedBackFeature1=new FeedBackFeature();
                feedBackFeature1.setUserName(userName);
                feedBackFeature1.setDisableOrenabled(true);
                feedBackFeature1.setFormType("rating");
                feedBackFeatureRepository.save(feedBackFeature1);

            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(true);
                feedBackFeatureRepository.save(feedbackFeature);

            }
        }
        else if(addAvailableButtonsRequest.getRating()==false){
            Optional<FeedBackFeature> contactFeedbackFeature = feedBackFeatureAns.stream()
                    .filter(feedbackFeatureAns -> "rating".equals(feedbackFeatureAns.getFormType()))
                    .findFirst();
            if(!contactFeedbackFeature.isPresent()){
                //do not have to make a new object
            }
            else{
                //set true;
                FeedBackFeature feedbackFeature = contactFeedbackFeature.get();
                feedbackFeature.setDisableOrenabled(false);
                feedBackFeatureRepository.save(feedbackFeature);


            }
        }
//
//        FeedBackFeature feedBackFeature1 = new FeedBackFeature();
//        feedBackFeature1.setUserName(userName);
//        if(addAvailableButtonsRequest.getContact()){
//            feedBackFeature1.setDisableOrenabled(true);
//        }
//        else{
//            feedBackFeature1.setDisableOrenabled(false);
//        }
//        feedBackFeature1.setFormType("contact");
//        //
//        FeedBackFeature feedBackFeature2 = new FeedBackFeature();
//        feedBackFeature2.setUserName(userName);
//        if(addAvailableButtonsRequest.getSuggestion()){
//            feedBackFeature2.setDisableOrenabled(true);
//        }
//        else{
//            feedBackFeature2.setDisableOrenabled(false);
//        }
//        feedBackFeature2.setFormType("suggestion");
//        //
//        FeedBackFeature feedBackFeature3 = new FeedBackFeature();
//        feedBackFeature3.setUserName(userName);
//        if(addAvailableButtonsRequest.getIssue()){
//            feedBackFeature3.setDisableOrenabled(true);
//        }
//        else{
//            feedBackFeature3.setDisableOrenabled(false);
//        }
//        feedBackFeature3.setFormType("issue");
//        //
//        FeedBackFeature feedBackFeature4 = new FeedBackFeature();
//        feedBackFeature4.setUserName(userName);
//        if(addAvailableButtonsRequest.getRating()){
//            feedBackFeature4.setDisableOrenabled(true);
//        }
//        else{
//            feedBackFeature4.setDisableOrenabled(false);
//        }
//        feedBackFeature4.setFormType("rating");
//        //
//        FeedBackFeature feedBackFeature5 = new FeedBackFeature();
//        feedBackFeature5.setUserName(userName);
//        if(addAvailableButtonsRequest.getFeedBack()){
//            feedBackFeature5.setDisableOrenabled(true);
//        }
//        else{
//            feedBackFeature5.setDisableOrenabled(false);
//        }
//        feedBackFeature5.setFormType("feedBack");
//
//
////       feedBackFeature.setContact(addAvailableButtonsRequest.getContact());
////       feedBackFeature.setSuggestion(addAvailableButtonsRequest.getSuggestion());
////       feedBackFeature.setIssue(addAvailableButtonsRequest.getIssue());
////       feedBackFeature.setUserName(userName);
////       feedBackFeature.setFeedBack(addAvailableButtonsRequest.getFeedBack());
//        feedBackFeatureRepository.save(feedBackFeature);
        //GetAvailabilityButtonsDto getAvailabilityButtonsDto = FeedBackFeatureMapper.converEntityToDTO(feedBackFeature);

        return new ResponseEntity<GetAvailableButtonsResponse>
                (new GetAvailableButtonsResponse
                        ("1", "The required buttons details are :", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetAvailableButtonsResponse> getAvailableButtons(String userName) {
        if (userName == null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "Invalid Request", null),
                            HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = userRepository.findByUserName(userName);
        if (userEntity == null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "This user does not exist in db", null),
                            HttpStatus.NOT_FOUND);

        }
        List<FeedBackFeature> feedBackFeature = feedBackFeatureRepository.findByUserNameAndDisableOrenabled(userName,true);
        if(feedBackFeature.isEmpty()){
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "No butons found", null),
                            HttpStatus.OK);
        }
        ArrayList<GetAvailabilityButtonsDto> list = new ArrayList<>();
        for (int j = 0; j < feedBackFeature.size(); j++) {
            GetAvailabilityButtonsDto getAvailabilityButtonsDto = FeedBackFeatureMapper.converEntityToDTO(feedBackFeature.get(j));
            list.add(getAvailabilityButtonsDto);
        }
        return new ResponseEntity<GetAvailableButtonsResponse>
                (new GetAvailableButtonsResponse
                        ("1", "The required buttons details are :", list), HttpStatus.OK);

    }
//    @Override
//    public ResponseEntity<GetAvailableButtonsResponse> disableAvailableButtons(@RequestParam(name = "userName") String userName, @RequestBody AddAvailableButtonsRequest addAvailableButtonsRequest) {
//        if (userName == null || addAvailableButtonsRequest == null) {
//            return new ResponseEntity<GetAvailableButtonsResponse>
//                    (new GetAvailableButtonsResponse
//                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
//
//        }
//        FeedBackFeature feedBackFeature= feedBackFeatureRepository.findByUserNameAndFormType(userName,addAvailableButtonsRequest.getFormType());
//
//        return new ResponseEntity<GetAvailableButtonsResponse>
//                    (new GetAvailableButtonsResponse ("1", "The button has been disabled:", null), HttpStatus.OK);
//
//
//
//
//    }

    @Override
    //Add suggestion for a mentor
    public ResponseEntity<AddSuggestionResponse> AddSuggestionForUser(String userName, AddSuggestionRequest addSuggestionRequest) {
        if (userName == null || addSuggestionRequest == null) {
            return new ResponseEntity<AddSuggestionResponse>
                    (new AddSuggestionResponse
                            ("0", "Invalid Request", null),
                            HttpStatus.BAD_REQUEST);
        }
        List<MultipartFile>list=addSuggestionRequest.getFileUrls();
        for(int i=0;i<list.size();i++){

        MultipartFile poster=list.get(i);


;            if (poster != null && !poster.isEmpty() &&
                    !("image/jpeg".equals(poster.getContentType()) || "image/png".equals(poster.getContentType()) || "image/svg+xml".equals(poster.getContentType()))) {
                return new ResponseEntity<AddSuggestionResponse>
                        (new AddSuggestionResponse
                                ("0", "Incorrect Format, Only jpeg, png, or svg images are supported", null),
                                HttpStatus.BAD_REQUEST);
            }

        }
        UserEntity user = userRepository.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<AddSuggestionResponse>
                    (new AddSuggestionResponse
                            ("0", "This user does not exist in db", null),
                            HttpStatus.NOT_FOUND);
        }

        Suggestion suggestion = new Suggestion();
        suggestion.setEmailId(addSuggestionRequest.getEmailId());
        ArrayList<String>listAns=new ArrayList<>();
      for(int j=0;j<list.size();j++) {
          String posterUrl = null;
          try {
              posterUrl = savePosterInBucketAndCreateUrl(list.get(j));
              listAns.add(posterUrl);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }


          // suggestion.setFileUrls(addSuggestionRequest.getFileUrls());

          // List<String>ans=new ArrayList<>();
          suggestion.setFileUrls(listAns);

        //System.out.print(posterUrl);
        suggestion.setDetails(addSuggestionRequest.getDetails());
        suggestion.setUserName(userName);
        suggestionRepository.save(suggestion);

        AddSuggestionResponseDTO addSuggestionResponseDTO = SuggestionMapper.converEntityToDTO(suggestion);
        return new ResponseEntity<AddSuggestionResponse>
                (new AddSuggestionResponse
                        ("1", "The suggestion has been saved:", addSuggestionResponseDTO), HttpStatus.OK);


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

@Override
    public ResponseEntity<GiveSuggestionResponse> getAllSuggestion(String userName) {
        if (userName == null) {
            return new ResponseEntity<GiveSuggestionResponse>
                    (new GiveSuggestionResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    UserEntity user = userRepository.findByUserName(userName);
    if (user == null) {
        return new ResponseEntity<GiveSuggestionResponse>
                    (new GiveSuggestionResponse
                            ("0", "This user does not exist in db", null),
                            HttpStatus.NOT_FOUND);
    }



        List<Suggestion> suggestion = suggestionRepository.findByUserName(userName);
        if(suggestion.isEmpty()){
            return new ResponseEntity<GiveSuggestionResponse>
                    (new GiveSuggestionResponse
                            ("0", "There are no suggestion  given to the mentor", null),
                            HttpStatus.NOT_FOUND);


        }
        List<AddSuggestionResponseDTO> addSuggestionResponseDTOList = new ArrayList<>();
        for (int i = 0; i < suggestion.size(); i++) {
            AddSuggestionResponseDTO addSuggestionResponseDTO = SuggestionMapper.converEntityToDTO(suggestion.get(i));
            addSuggestionResponseDTOList.add(addSuggestionResponseDTO);
        }
        return new ResponseEntity<GiveSuggestionResponse>
                (new GiveSuggestionResponse
                        ("1", "The suggestions are :", addSuggestionResponseDTOList), HttpStatus.OK);


    }
    @Override
    public ResponseEntity<AddFeedBackDetailsResponse> addFeedBackDetailsOfUser(String userName,AddFeedBackFeatureDetailsRequest addFeedBackFeatureDetailsRequest) {
        if (userName == null || addFeedBackFeatureDetailsRequest.getFileUrls() == null || addFeedBackFeatureDetailsRequest.getDetails() == null || addFeedBackFeatureDetailsRequest.getEmailId() == null) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        UserEntity userEntity =userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "No user exist in db", null), HttpStatus.NOT_FOUND);
        }
        List<MultipartFile>list=addFeedBackFeatureDetailsRequest.getFileUrls();
        for(int i=0;i<list.size();i++){
            MultipartFile poster =list.get(i);


            if (poster != null && !poster.isEmpty() &&
                    !("image/jpeg".equals(poster.getContentType()) || "image/png".equals(poster.getContentType()) || "image/svg+xml".equals(poster.getContentType()))) {
                return new ResponseEntity<AddFeedBackDetailsResponse>
                        (new AddFeedBackDetailsResponse
                                ("0", "Incorrect Format, Only jpeg, png, or svg images are supported", null),
                                HttpStatus.BAD_REQUEST);
            }

        }


        FeedBackDetails feedBackDetails = new FeedBackDetails();
        feedBackDetails.setEmailId(addFeedBackFeatureDetailsRequest.getEmailId());
        feedBackDetails.setUserName(userName);
        feedBackDetails.setDetails(addFeedBackFeatureDetailsRequest.getDetails());
        ArrayList<String>listAns=new ArrayList<>();
        for(int j=0;j<list.size();j++) {
            String posterUrl = null;
            try {
                posterUrl = savePosterInBucketAndCreateUrl(list.get(j));
                listAns.add(posterUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // suggestion.setFileUrls(addSuggestionRequest.getFileUrls());

        // List<String>ans=new ArrayList<>();
        feedBackDetails.setFileUrls(listAns);
        //feedBackDetails.setFileUrls(addFeedBackFeatureDetailsRequest.getFileUrls());
        feedBackDetailsRepository.save(feedBackDetails);
        return new ResponseEntity<AddFeedBackDetailsResponse>
                (new AddFeedBackDetailsResponse
                        ("1", "FeedBack saved", null), HttpStatus.OK);
    }
    @Override
   public  ResponseEntity<AddFeedBackDetailsResponse> getFeedBackDetailsOfUser(String userName){
       if (userName == null ) {
           return new ResponseEntity<AddFeedBackDetailsResponse>
                   (new AddFeedBackDetailsResponse
                           ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

       }
       UserEntity userEntity =userRepository.findByUserName(userName);
       if(userEntity==null){
           return new ResponseEntity<AddFeedBackDetailsResponse>
                   (new AddFeedBackDetailsResponse
                           ("0", "Invalid Request", null), HttpStatus.NOT_FOUND);

       }
      List<FeedBackDetails> feedBackDetails= feedBackDetailsRepository.findByUserName(userName);
       if(feedBackDetails.isEmpty()){
           return new ResponseEntity<AddFeedBackDetailsResponse>
                   (new AddFeedBackDetailsResponse
                           ("0", "No feedback found for this user", null), HttpStatus.OK);
       }
       List<AddFeedBackDetailsResponseDTO> addSuggestionResponseDTOList = new ArrayList<>();
       for(int i=0;i<feedBackDetails.size();i++) {
           AddFeedBackDetailsResponseDTO addFeedBackDetailsResponseDTO=FeedBackFeatureMapper.convertEntityToDTO2(feedBackDetails.get(i));
           addSuggestionResponseDTOList.add(addFeedBackDetailsResponseDTO);
       }
       return new ResponseEntity<AddFeedBackDetailsResponse>
               (new AddFeedBackDetailsResponse
                       ("1", "The feedbacks are :",addSuggestionResponseDTOList ), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<AddFeedBackDetailsResponse> addIssue(String  userName,AddFeedBackFeatureDetailsRequest addFeedBackFeatureDetailsRequest){
        if (userName == null || addFeedBackFeatureDetailsRequest.getFileUrls() == null || addFeedBackFeatureDetailsRequest.getDetails() == null || addFeedBackFeatureDetailsRequest.getEmailId() == null) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
      UserEntity userEntity=  userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "No user exist in db", null), HttpStatus.NOT_FOUND);
        }
        List<MultipartFile>list=addFeedBackFeatureDetailsRequest.getFileUrls();
        for(int i=0;i<list.size();i++){
            MultipartFile poster =list.get(i);


            if (poster != null && !poster.isEmpty() &&
                    !("image/jpeg".equals(poster.getContentType()) || "image/png".equals(poster.getContentType()) || "image/svg+xml".equals(poster.getContentType()))) {
                return new ResponseEntity<AddFeedBackDetailsResponse>
                        (new AddFeedBackDetailsResponse
                                ("0", "Incorrect Format, Only jpeg, png, or svg images are supported", null),
                                HttpStatus.BAD_REQUEST);
            }

        }
        Issue issue=new Issue();
        issue.setDetails(addFeedBackFeatureDetailsRequest.getDetails());
        issue.setUserName(userName);
        ArrayList<String>listAns=new ArrayList<>();
        for(int j=0;j<list.size();j++) {
            String posterUrl = null;
            try {
                posterUrl = savePosterInBucketAndCreateUrl(list.get(j));
                listAns.add(posterUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // suggestion.setFileUrls(addSuggestionRequest.getFileUrls());

        // List<String>ans=new ArrayList<>();
        issue.setFileUrls(listAns);

        issue.setEmailId(addFeedBackFeatureDetailsRequest.getEmailId());
      //  issue.setFileUrls(addFeedBackFeatureDetailsRequest.getFileUrls());
        issueRepository.save(issue);
        return new ResponseEntity<AddFeedBackDetailsResponse>
                (new AddFeedBackDetailsResponse
                        ("1", "The issue has been added:",null ), HttpStatus.OK);




    }
    @Override
   public  ResponseEntity<AddFeedBackDetailsResponse>getIssue(String userName){
        if(userName==null){
            return new ResponseEntity<AddFeedBackDetailsResponse>
                        (new AddFeedBackDetailsResponse
                                ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
       UserEntity userEntity= userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "The user does not exist", null), HttpStatus.NOT_FOUND);

        }
       List<Issue> issue= issueRepository.findByUserName(userName);
        if(issue.isEmpty()){
            return new ResponseEntity<AddFeedBackDetailsResponse>
                (new AddFeedBackDetailsResponse
                        ("0", "No issue exist in the db", null), HttpStatus.OK);


        }

    List<AddFeedBackDetailsResponseDTO> addSuggestionResponseDTOList = new ArrayList<>();
       for(int i=0;i<issue.size();i++) {
        AddFeedBackDetailsResponseDTO addFeedBackDetailsResponseDTO=FeedBackFeatureMapper.convertEntityToDTO3(issue.get(i));
        addSuggestionResponseDTOList.add(addFeedBackDetailsResponseDTO);
    }
       return new ResponseEntity<AddFeedBackDetailsResponse>
               (new AddFeedBackDetailsResponse
            ("1", "The Issues are :",addSuggestionResponseDTOList ), HttpStatus.OK);



   }
   @Override
   public  ResponseEntity<AddContactResponse>addContactDetailsOfUser(String userName,AddContactRequest addContactRequest){
        if(userName==null || addContactRequest==null){
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
       UserEntity userEntity= userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.NOT_FOUND);
        }
        Contact contact=new Contact();
        contact.setDetails(addContactRequest.getDetails());
        contact.setName(addContactRequest.getName());
        contact.setEmailId(addContactRequest.getEmail());
        contact.setUserName(userName);
        contact.setPhoneNumber(addContactRequest.getPhoneNumber());
        contactRepository.save(contact);
       return new ResponseEntity<AddContactResponse>
               (new AddContactResponse
                       ("1", "The contact details has been saved  :",null ), HttpStatus.OK);





    }
    @Override
      public   ResponseEntity<AddContactResponse>getContactDetailsOfUser(String userName){
        if(userName==null){
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
       UserEntity userEntity= userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "No user found", null), HttpStatus.NOT_FOUND);

        }
      List<Contact> contact= contactRepository.findByUserName(userName);
        if(contact.isEmpty()){
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "No Contact details exist", null), HttpStatus.OK);

        }
          List<AddContactResponseDTO> addSuggestionResponseDTOList = new ArrayList<>();
        for(int j=0;j<contact.size();j++) {
            AddContactResponseDTO addContactResponseDTO= FeedBackFeatureMapper.converEntityToDTO3(contact.get(j));
            addSuggestionResponseDTOList.add(addContactResponseDTO);

        }
        return new ResponseEntity<AddContactResponse>
                  (new AddContactResponse
                          ("1", "The Contact detials list is :", addSuggestionResponseDTOList ), HttpStatus.OK);




    }
    @Override
   public  ResponseEntity<AddContactResponse>addRating(String userName,  AddRatingRequest addRatingRequest){
       if(userName==null || addRatingRequest==null || addRatingRequest.getNumberOfStars()==null){
           return new ResponseEntity<AddContactResponse>
                   (new AddContactResponse
                           ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
       }
      UserEntity userEntity= userRepository.findByUserName(userName);
       if(userEntity==null){
           return new ResponseEntity<AddContactResponse>
                   (new AddContactResponse
                           ("0", "No user found in the db", null), HttpStatus.NOT_FOUND);
       }
       Rating rating=new Rating();
       rating.setUserName(userName);
       rating.setNumberOfStars(addRatingRequest.getNumberOfStars());
       rating.setEmailId(addRatingRequest.getEmailId());
       ratingRepository.save(rating);
       return new ResponseEntity<AddContactResponse>
               (new AddContactResponse
                       ("1", "The rating has been saved:",null  ), HttpStatus.OK);




   }
   @Override
   public  ResponseEntity<AddRatingResponse> getRatingDetails(String userName){
        if(userName==null){
            return new ResponseEntity<AddRatingResponse>
                    (new AddRatingResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity=userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<AddRatingResponse>
                    (new AddRatingResponse
                            ("0", "Invalid Request", null), HttpStatus.NOT_FOUND);
        }
       List<Rating> ratingList= ratingRepository.findByUserName(userName);
        if(ratingList.isEmpty()){
            return new ResponseEntity<AddRatingResponse>
                    (new AddRatingResponse
                            ("0", "No rating exists in the db", null), HttpStatus.OK);

        }
        List<AddRatingResponseDTO>addRatingResponseDTOSList=new ArrayList<>();
        for(int j=0;j<ratingList.size();j++){
            AddRatingResponseDTO addRatingResponseDTO= FeedBackFeatureMapper.converEntityToDTO4(ratingList.get(j));
            addRatingResponseDTOSList.add(addRatingResponseDTO);
        }
       return new ResponseEntity<AddRatingResponse>
               (new AddRatingResponse
                       ("1", "The Rating list are :", addRatingResponseDTOSList ), HttpStatus.OK);



   }







}
