package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.services.CreatorFeatureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/CreatorFeature")
public class CreatorFeatureInfoController {
    @Autowired
    CreatorFeatureInfoService creatorFeatureInfoService;
    @PostMapping("/AddFeatureDetails")
    public ResponseEntity<CreateDetailsForCreatorResponse>AddFeatureDetails(@RequestParam(name="userId") UUID userId , @RequestBody CreateDetailsRequest createDetailsRequest){
        if (createDetailsRequest == null ||
                createDetailsRequest.getMasterClass() == null || createDetailsRequest.getLeadGenForm() == null ||
                createDetailsRequest.getSlot() == null || createDetailsRequest.getLandingPageRequest() == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }try {
                return creatorFeatureInfoService.AddCreatorPersonalieFeature(userId, createDetailsRequest);
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
