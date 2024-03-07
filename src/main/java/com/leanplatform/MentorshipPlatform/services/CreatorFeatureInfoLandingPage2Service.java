package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2.CreateDetailsResponseForCreatorLP2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CreatorFeatureInfoLandingPage2Service {
    public ResponseEntity<CreateDetailsForCreatorResponse> AddCreatorPersonalieFeatureLP2(String userName, CreatorDetailsRequestLP2 createDetailsRequest);
    public ResponseEntity<CreateDetailsResponseForCreatorLP2>GetCreatorPersonalieFeature1(String  userName);
   public  ResponseEntity<UpdateSlotResponse>UpdateSlotButton2(String userName, UpdateSlotRequest updateSlotRequest);



}
