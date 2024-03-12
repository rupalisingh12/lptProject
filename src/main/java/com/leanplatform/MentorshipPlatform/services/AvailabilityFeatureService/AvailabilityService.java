package com.leanplatform.MentorshipPlatform.services.AvailabilityFeatureService;

import com.leanplatform.MentorshipPlatform.controllers.UserFeature.DisplayMentorAvailability;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.*;
import com.leanplatform.MentorshipPlatform.entities.AvailabiliyFeature.Availability;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AvailabilityService {
    ResponseEntity<AvailabilityByMentorResponse> createAvailability(AvailabilityByMentor availabilityByMentor);

    List<Availability> findAll();

    ResponseEntity<AvailabilityListOfMentor> getDesiredMentorAvailability(DisplayMentorAvailability displayMentorAvailability);

    ResponseEntity<MentorAvailabilityDeletedResponse> deleteMentorAvailability(DeleteMentorAvailability deleteMentorAvailability);

    ResponseEntity<AvailabilityByMentorResponse> updateMentorAvailability(UpdateMentorAvailability updateMentorAvailability);
}
