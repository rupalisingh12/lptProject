package com.leanplatform.MentorshipPlatform.dto.EventTypesController;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DeleteEventResponse {
    private String statusCode;
    private String responseMessage;
    private UUID eventId;

}
