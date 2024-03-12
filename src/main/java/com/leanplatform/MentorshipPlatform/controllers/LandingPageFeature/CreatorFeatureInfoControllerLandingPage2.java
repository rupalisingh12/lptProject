package com.leanplatform.MentorshipPlatform.controllers.LandingPageFeature;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2.CreateDetailsResponseForCreatorLP2;
import com.leanplatform.MentorshipPlatform.services.LandingPageFeatureService.CreatorFeatureInfoLandingPage2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CreatorFeatureLandingPage2")
public class CreatorFeatureInfoControllerLandingPage2 {
    @Autowired
    CreatorFeatureInfoLandingPage2Service creatorFeatureInfoLandingPage2Service;
    @PostMapping("/AddFeatureDetailsLP2")
    public ResponseEntity<CreateDetailsForCreatorResponse> AddFeatureDetails1(@RequestParam(name="userName") String userName , @RequestBody CreatorDetailsRequestLP2 creatorDetailsRequestLP2){
        if (creatorDetailsRequestLP2 == null ||
                creatorDetailsRequestLP2.getMasterClass() == null || creatorDetailsRequestLP2.getLeadGenForm() == null ||
                creatorDetailsRequestLP2.getSlot() == null || creatorDetailsRequestLP2.getLandingPageRequest2() == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }try {
            return creatorFeatureInfoLandingPage2Service.AddCreatorPersonalieFeatureLP2(userName, creatorDetailsRequestLP2);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/GetFeatureDetailsLP2")
    public ResponseEntity<CreateDetailsResponseForCreatorLP2>GetFeatureDetails(@RequestParam(name="userName") String userName ){
        if (userName==null) {
            return new ResponseEntity<>
                    (new CreateDetailsResponseForCreatorLP2
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }try {
            return creatorFeatureInfoLandingPage2Service.GetCreatorPersonalieFeature1(userName);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateDetailsResponseForCreatorLP2
                    (
                            "0",
                            "Caught in catch block   "+ e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateSlotLP2")
    public ResponseEntity<UpdateSlotResponse>updateSlotbutton2(@RequestParam(name="userName")String userName, @RequestBody UpdateSlotRequest updateSlotRequest){
        if(userName==null){
            return new ResponseEntity<>
                    (new UpdateSlotResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try{
            return creatorFeatureInfoLandingPage2Service.UpdateSlotButton2(userName,updateSlotRequest);
        }catch(Exception e) {
            return new ResponseEntity<>(new UpdateSlotResponse
                    (
                            "0",
                            "Caught in catch block   "+ e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
    }

}
