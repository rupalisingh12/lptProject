package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.AddServiceResponse;
import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.ServiceToAdd;
import org.springframework.http.ResponseEntity;

public interface ServicesOfferedService {
    ResponseEntity<AddServiceResponse> createNewService(ServiceToAdd serviceToAdd);
}
