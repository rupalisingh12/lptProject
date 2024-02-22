package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreatorDetailsRequestLP2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CreatorFeatureInfoLandingPage2Service {
    public ResponseEntity<CreateDetailsForCreatorResponse> AddCreatorPersonalieFeatureLP2(UUID userId, CreatorDetailsRequestLP2 createDetailsRequest);


}
