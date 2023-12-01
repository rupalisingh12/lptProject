package com.leanplatform.MentorshipPlatform.dto.SessionController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionsListResponse {
    private String statusCode;
    private String responseMessage;
    private List<SessionsDto> sessionsDtos;
}
