package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.*;
import com.leanplatform.MentorshipPlatform.entities.*;
import com.leanplatform.MentorshipPlatform.mappers.FeedBackFeatureMapper;
import com.leanplatform.MentorshipPlatform.mappers.SuggestionMapper;
import com.leanplatform.MentorshipPlatform.repositories.*;
import com.leanplatform.MentorshipPlatform.services.FeedBackFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        FeedBackFeature feedBackFeature1 = feedBackFeatureRepository.findByUserNameAndFormType(userName, addAvailableButtonsRequest.getFormType());
        if (feedBackFeature1 != null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "The user with same formType already exists", null),
                            HttpStatus.BAD_REQUEST);
        }
        FeedBackFeature feedBackFeature = new FeedBackFeature();
        feedBackFeature.setUserName(userName);
        feedBackFeature.setFormType(addAvailableButtonsRequest.getFormType());

//       feedBackFeature.setContact(addAvailableButtonsRequest.getContact());
//       feedBackFeature.setSuggestion(addAvailableButtonsRequest.getSuggestion());
//       feedBackFeature.setIssue(addAvailableButtonsRequest.getIssue());
//       feedBackFeature.setUserName(userName);
//       feedBackFeature.setFeedBack(addAvailableButtonsRequest.getFeedBack());
        feedBackFeatureRepository.save(feedBackFeature);
        GetAvailabilityButtonsDto getAvailabilityButtonsDto = FeedBackFeatureMapper.converEntityToDTO(feedBackFeature);

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
        List<FeedBackFeature> feedBackFeature = feedBackFeatureRepository.findByUserName(userName);
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

    @Override
    //Add suggestion for a mentor
    public ResponseEntity<AddSuggestionResponse> AddSuggestionForUser(String userName, AddSuggestionRequest addSuggestionRequest) {
        if (userName == null || addSuggestionRequest == null) {
            return new ResponseEntity<AddSuggestionResponse>
                    (new AddSuggestionResponse
                            ("0", "Invalid Request", null),
                            HttpStatus.BAD_REQUEST);
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
        suggestion.setFileUrls(addSuggestionRequest.getFileUrls());
        suggestion.setDetails(addSuggestionRequest.getDetails());
        suggestion.setUserName(userName);
        suggestionRepository.save(suggestion);

        AddSuggestionResponseDTO addSuggestionResponseDTO = SuggestionMapper.converEntityToDTO(suggestion);
        return new ResponseEntity<AddSuggestionResponse>
                (new AddSuggestionResponse
                        ("1", "The suggestion has been saved:", addSuggestionResponseDTO), HttpStatus.OK);


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

        FeedBackDetails feedBackDetails = new FeedBackDetails();
        feedBackDetails.setEmailId(addFeedBackFeatureDetailsRequest.getEmailId());
        feedBackDetails.setUserName(userName);
        feedBackDetails.setDetails(addFeedBackFeatureDetailsRequest.getDetails());
        feedBackDetails.setFileUrls(addFeedBackFeatureDetailsRequest.getFileUrls());
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
        Issue issue=new Issue();
        issue.setDetails(addFeedBackFeatureDetailsRequest.getDetails());
        issue.setUserName(userName);
        issue.setEmailId(addFeedBackFeatureDetailsRequest.getEmailId());
        issue.setFileUrls(addFeedBackFeatureDetailsRequest.getFileUrls());
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
