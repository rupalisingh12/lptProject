package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreatorDetailsRequestLP2;
import com.leanplatform.MentorshipPlatform.services.CreatorFeatureInfoLandingPage2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreatorFeatureInfoLandingPage2ServiceImpl  extends CreatorFeatureInfoLandingPage2Service {
    public ResponseEntity<CreateDetailsForCreatorResponse> AddCreatorPersonalieFeatureLP2(UUID userId, CreatorDetailsRequestLP2 creatorDetailsRequestLP2 ){

    }
}
