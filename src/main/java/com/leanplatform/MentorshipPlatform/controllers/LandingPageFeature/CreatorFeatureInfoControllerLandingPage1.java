package com.leanplatform.MentorshipPlatform.controllers.LandingPageFeature;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddHeadingRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.UpdateSlotRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.UpdateSlotResponse;
import com.leanplatform.MentorshipPlatform.services.LandingPageFeatureService.CreatorFeatureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CreatorFeature")
public class CreatorFeatureInfoControllerLandingPage1 {
    @Autowired
    CreatorFeatureInfoService creatorFeatureInfoService;
    @PostMapping("/AddFeatureDetails")
    public ResponseEntity<CreateDetailsForCreatorResponse>AddFeatureDetails(@RequestParam(name="userName") String userName , @RequestBody CreateDetailsRequest createDetailsRequest){
        if (createDetailsRequest == null ||
                createDetailsRequest.getMasterClass() == null || createDetailsRequest.getLeadGenForm() == null ||
                createDetailsRequest.getSlot() == null || createDetailsRequest.getLandingPageRequest() == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }try {
                return creatorFeatureInfoService.AddCreatorPersonalieFeature(userName, createDetailsRequest);
            } catch (Exception e) {
                return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                        (
                                "0",
                                "Caught in catch block   "+ e.getLocalizedMessage(),
                                null
                        ), HttpStatus.BAD_REQUEST);
            }
        }
    @GetMapping("/GetFeatureDetails")
    public ResponseEntity<CreateDetailsForCreatorResponse>GetFeatureDetails(@RequestParam(name="userName") String userName,@RequestParam("flag") Boolean flag ){
        if (userName==null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }try {
            return creatorFeatureInfoService.GetCreatorPersonalieFeature(userName,flag);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                    (
                            "0",
                            "Caught in catch block   "+ e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateSlotLP1")
    public ResponseEntity<UpdateSlotResponse>updateSlotbutton(@RequestParam(name="userName")String userName, @RequestBody UpdateSlotRequest updateSlotRequest){
        if(userName==null){
            return new ResponseEntity<>
                    (new UpdateSlotResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try{
            return creatorFeatureInfoService.UpdateSlotButton(userName,updateSlotRequest);
        }catch(Exception e) {
            return new ResponseEntity<>(new UpdateSlotResponse
                    (
                            "0",
                            "Caught in catch block   "+ e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
    }
    @PostMapping("/addHeadings")
    public ResponseEntity<AddCoursesResponse> addHeading(@RequestParam("userName") String userName, @RequestBody AddHeadingRequest addHeadingRequest) {
        if (userName == null || addHeadingRequest==null) {
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0",
                            "Null request recieved ", null,null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return creatorFeatureInfoService.AddsHeading(userName, addHeadingRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new AddCoursesResponse
                    ("0","Caught in the catch block"+e.getLocalizedMessage()
                            , null,null
                    ), HttpStatus.BAD_REQUEST);

        }
    }



}
