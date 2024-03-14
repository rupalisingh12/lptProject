package com.leanplatform.MentorshipPlatform.dto.UserRegistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmsPayload {

    @JsonProperty("number")
    private String[] number;

    @JsonProperty("message")
    private String message;

    @JsonProperty("senderId")
    private String senderId;

    @JsonProperty("templateId")
    private String templateId;
}


