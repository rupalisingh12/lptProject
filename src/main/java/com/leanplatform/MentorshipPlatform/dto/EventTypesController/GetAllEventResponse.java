package com.leanplatform.MentorshipPlatform.dto.EventTypesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllEventResponse {
    private String statusCode;
    private String responseMessage;
    private List<CreateEventDTO> createEvent;
}
