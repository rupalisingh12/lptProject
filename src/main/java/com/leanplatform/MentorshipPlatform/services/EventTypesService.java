package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.EventTypesController.CreateEventRequestObject;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.CreateEventResponse;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.DeleteEventResponse;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.GetAllEventResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EventTypesService {
    ResponseEntity<CreateEventResponse>createEvent(CreateEventRequestObject createEventRequestObject, UUID userId);
    ResponseEntity<GetAllEventResponse>getALLEventsOfAUser(UUID userId);
    ResponseEntity<CreateEventResponse>getEventOfAUser(UUID eventId,UUID userId);
//    ResponseEntity<CreateEventResponse>updateEvent(UUID eventId,UUID userId);
    ResponseEntity<DeleteEventResponse>deleteAEvent(UUID eventId, UUID userId);




}
