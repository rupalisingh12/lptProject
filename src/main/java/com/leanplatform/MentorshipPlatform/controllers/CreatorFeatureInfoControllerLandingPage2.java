package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreatorDetailsRequestLP2;
import com.leanplatform.MentorshipPlatform.services.CreatorFeatureInfoLandingPage2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/CreatorFeatureLandingPage2")
public class CreatorFeatureInfoControllerLandingPage2 {
    @Autowired
    CreatorFeatureInfoLandingPage2Service creatorFeatureInfoLandingPage2Service;
    @PostMapping("/AddFeatureDetailsLP2")
    public ResponseEntity<CreateDetailsForCreatorResponse> AddFeatureDetails(@RequestParam(name="userId") UUID userId , @RequestBody CreatorDetailsRequestLP2 creatorDetailsRequestLP2){
        if (creatorDetailsRequestLP2 == null ||
                creatorDetailsRequestLP2.getMasterClass() == null || creatorDetailsRequestLP2.getLeadGenForm() == null ||
                creatorDetailsRequestLP2.getSlot() == null || creatorDetailsRequestLP2.getLandingPageRequest2() == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }try {
            return creatorFeatureInfoLandingPage2Service.AddCreatorPersonalieFeatureLP2(userId, creatorDetailsRequestLP2);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }
}
