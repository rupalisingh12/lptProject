package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.MentorController.*;
import org.springframework.http.ResponseEntity;

public interface ServicesByMentorsService {

    ResponseEntity<MentorAddedServiceResponse> addServiceByMentor(MentorAddsServices mentorAddsServices);

    ResponseEntity<UpdateMentorServiceResponse> serviceUpdate(UpdateMentorServiceObject updateMentorServiceObject);

    ResponseEntity<DeleteMentorServiceResponse> deleteService(DeleteMentorServiceObject deleteMentorServiceObject);
}
