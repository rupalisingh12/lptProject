package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import com.leanplatform.MentorshipPlatform.dto.BookingController.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDetailsRequest {

    private String leadGenForm;
    private String masterClass;
    private Boolean slot;
    private LandingPageRequest landingPageRequest;
}
