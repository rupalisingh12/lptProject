package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.*;
import com.leanplatform.MentorshipPlatform.services.FeedBackFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/FeedBackFeature")
public class FeedBackFeatureController {
    @Autowired
    FeedBackFeatureService feedBackFeatureService;


    //this will be called from the personal dashBoard of a mentor(user)
    @PostMapping("/buttonsToAdd")
    public ResponseEntity<GetAvailableButtonsResponse> AddAvailableButtons(@RequestParam(name = "userName") String userName, @RequestBody AddAvailableButtonsRequest addAvailableButtonsRequest) {
        if (userName == null || addAvailableButtonsRequest == null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }

        try {
            return feedBackFeatureService.AddAvailableButtons(userName, addAvailableButtonsRequest);

        } catch (Exception e) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "Invalid Request:"+e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);


        }
    }

    //these buttons will be shown on the website of the mentor(user) and mentee will be allowed to choose from the available buttons
    @GetMapping("/buttonsOption")
    public ResponseEntity<GetAvailableButtonsResponse> GetAvailableButtons(@RequestParam(name = "userName") String userName) {
        if (userName == null) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return feedBackFeatureService.getAvailableButtons(userName);


        } catch (Exception e) {
            return new ResponseEntity<GetAvailableButtonsResponse>
                    (new GetAvailableButtonsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }

    }
//    @PutMapping("/disableTheButtons")
//    public ResponseEntity<GetAvailableButtonsResponse> updateAvailableButtons(@RequestParam(name = "userName") String userName, @RequestBody AddAvailableButtonsRequest addAvailableButtonsRequest) {
//        if (userName == null || addAvailableButtonsRequest == null) {
//            return new ResponseEntity<GetAvailableButtonsResponse>
//                    (new GetAvailableButtonsResponse
//                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
//
//        }
//
//        try {
//            return feedBackFeatureService.disableAvailableButtons(userName, addAvailableButtonsRequest);
//
//        } catch (Exception e) {
//            return new ResponseEntity<GetAvailableButtonsResponse>
//                    (new GetAvailableButtonsResponse
//                            ("0", "Invalid Request:"+e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);
//
//
//        }
//    }
    //add the details for suggestion button
    @PostMapping("/suggestion")
    public ResponseEntity<AddSuggestionResponse> addSuggestion(@RequestParam(name = "userName") String userName, @ModelAttribute AddSuggestionRequest addSuggestionRequest) {
        if (userName == null) {
            return new ResponseEntity<AddSuggestionResponse>
                    (new AddSuggestionResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        List<MultipartFile>list=addSuggestionRequest.getFileUrls();
        for(int i=0;i<list.size();i++){
        MultipartFile poster =list.get(i);


            if (poster != null && !poster.isEmpty() &&
                    !("image/jpeg".equals(poster.getContentType()) || "image/png".equals(poster.getContentType()) || "image/svg+xml".equals(poster.getContentType()))) {
                return new ResponseEntity<AddSuggestionResponse>
                        (new AddSuggestionResponse
                                ("0", "Incorrect Format, Only jpeg, png, or svg images are supported", null),
                                HttpStatus.BAD_REQUEST);
            }

        }
                try {
            return feedBackFeatureService.AddSuggestionForUser(userName, addSuggestionRequest);

        } catch (Exception e) {

            return new ResponseEntity<AddSuggestionResponse>
                    (new AddSuggestionResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    }

    //get suggestion on the dashborad
    @GetMapping("/getSuggestion")
    public ResponseEntity<GiveSuggestionResponse> getSuggestion(@RequestParam(name = "userName") String userName) {
        if (userName == null) {
            return new ResponseEntity<GiveSuggestionResponse>
                    (new GiveSuggestionResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        try {
            return feedBackFeatureService.getAllSuggestion(userName);
        } catch (Exception e) {
            return new ResponseEntity<GiveSuggestionResponse>
                    (new GiveSuggestionResponse
                            ("0", "Invalid Request"+e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }


    //add feedback
    @PostMapping("/addFeedBackforUser")
    public ResponseEntity<AddFeedBackDetailsResponse> addFeedBackDetails(@RequestParam(name = "userName") String userName, @ModelAttribute AddFeedBackFeatureDetailsRequest addFeedBackFeatureDetailsRequest) {
        if (userName == null || addFeedBackFeatureDetailsRequest.getFileUrls() == null || addFeedBackFeatureDetailsRequest.getDetails() == null || addFeedBackFeatureDetailsRequest.getEmailId() == null) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

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
        try {
            return feedBackFeatureService.addFeedBackDetailsOfUser(userName, addFeedBackFeatureDetailsRequest);
        } catch (Exception e) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/getFeedBackforUser")
    public ResponseEntity<AddFeedBackDetailsResponse> getFeedBackDetails(@RequestParam(name = "userName") String userName) {
        if (userName == null) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        try {
            return feedBackFeatureService.getFeedBackDetailsOfUser(userName);
        } catch (Exception e) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/addIssue")
    public ResponseEntity<AddFeedBackDetailsResponse> addIssue(@RequestParam(name = "userName") String userName, @ModelAttribute AddFeedBackFeatureDetailsRequest addFeedBackFeatureDetailsRequest) {
        if (userName == null || addFeedBackFeatureDetailsRequest.getFileUrls() == null || addFeedBackFeatureDetailsRequest.getDetails() == null || addFeedBackFeatureDetailsRequest.getEmailId() == null) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

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
        try {
            return feedBackFeatureService.addIssue(userName, addFeedBackFeatureDetailsRequest);
        } catch (Exception e) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }

    }


    @GetMapping("/getIssue")
    public ResponseEntity<AddFeedBackDetailsResponse> getIssue(@RequestParam(name = "userName") String userName) {
        if (userName == null) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
        try {
            return feedBackFeatureService.getIssue(userName);

        } catch (Exception e) {
            return new ResponseEntity<AddFeedBackDetailsResponse>
                    (new AddFeedBackDetailsResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/addContact")
    public ResponseEntity<AddContactResponse>addCOntactDetails(@RequestParam(name="userName")String userName,@RequestBody AddContactRequest addContactRequest) {
        if (userName == null || addContactRequest == null || addContactRequest.getName() == null || addContactRequest.getEmail() == null || addContactRequest.getPhoneNumber() == null || addContactRequest.getDetails() == null) {
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }

        try {
            return feedBackFeatureService.addContactDetailsOfUser(userName, addContactRequest);
        } catch (Exception e) {
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/getContact")
    public ResponseEntity<AddContactResponse>getContactDetails(@RequestParam(name="userName")String userName) {
        if (userName == null) {
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        try {
            return feedBackFeatureService.getContactDetailsOfUser(userName);
        } catch (Exception e) {
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/addRating")
    public ResponseEntity<AddContactResponse>addRatingDetails(@RequestParam(name="userName")String userName ,@RequestBody AddRatingRequest addRatingRequest){
        if(userName==null || addRatingRequest==null || addRatingRequest.getNumberOfStars()==null){
            return new ResponseEntity<AddContactResponse>
                    (new AddContactResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try{
           return feedBackFeatureService.addRating(userName,addRatingRequest);
        }
        catch (Exception e) {
                return new ResponseEntity<AddContactResponse>
                        (new AddContactResponse
                                ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


            }
    }
    @GetMapping("/getRating")
    public ResponseEntity<AddRatingResponse>addRatingDetails(@RequestParam(name="userName")String userName ){
        if(userName==null){
            new ResponseEntity<AddRatingResponse>
                    (new AddRatingResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try{
            return feedBackFeatureService.getRatingDetails(userName);
        }
        catch(Exception e) {
            return new ResponseEntity<AddRatingResponse>
                    (new AddRatingResponse
                            ("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    }


}









