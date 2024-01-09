package com.leanplatform.MentorshipPlatform.dto.UserController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserGetResponse {
    private String statusCode;
    private String responseMessage;
    private UserGetResponseDto users;
}
