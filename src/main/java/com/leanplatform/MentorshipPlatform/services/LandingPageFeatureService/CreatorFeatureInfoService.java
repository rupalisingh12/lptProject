package com.leanplatform.MentorshipPlatform.services.LandingPageFeatureService;

import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingResponse;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponse;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddHeadingRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.UpdateSlotRequest;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.UpdateSlotResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CreatorFeatureInfoService {
    public ResponseEntity<CreateDetailsForCreatorResponse>AddCreatorPersonalieFeature(String userName,  CreateDetailsRequest createDetailsRequest);

    public ResponseEntity<CreateDetailsForCreatorResponse>GetCreatorPersonalieFeature(String userId,Boolean flag);
    ResponseEntity<UpdateSlotResponse>UpdateSlotButton(String userName, UpdateSlotRequest updateSlotRequest);
    ResponseEntity<AddCoursesResponse>AddsHeading( String userName, AddHeadingRequest addHeadingRequest);


}
