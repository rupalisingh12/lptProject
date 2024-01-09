package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.EventTypesController.*;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EventTypesService {
    ResponseEntity<CreateEventResponse>createEvent(CreateEventRequestObject createEventRequestObject, UUID userId);
    ResponseEntity<GetAllEventResponse>getALLEventsOfAUser(UUID userId);
    ResponseEntity<CreateEventResponse>getEventOfAUser(UUID eventId,UUID userId);
    ResponseEntity<CreateEventResponse>updateEvent(UUID eventId, UUID userId, UpdateEventRequest updateEventRequest);
    ResponseEntity<DeleteEventResponse>deleteAEvent(UUID eventId, UUID userId);




}
