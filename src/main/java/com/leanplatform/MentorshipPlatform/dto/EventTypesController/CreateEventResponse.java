package com.leanplatform.MentorshipPlatform.dto.EventTypesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEventResponse {
    private String statusCode;
    private String responseMessage;
    private CreateEventDTO createEvent;

}
