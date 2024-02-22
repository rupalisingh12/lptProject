package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CreatorFeatureInfoService {
    public ResponseEntity<CreateDetailsForCreatorResponse>AddCreatorPersonalieFeature(UUID userId,  CreateDetailsRequest createDetailsRequest);

}
