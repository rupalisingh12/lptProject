package com.leanplatform.MentorshipPlatform.dto.MentorAccountController;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//User data has to put here. By a UserDataDto
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MentorRequestResponse {
    private String statusCode;
    private String responseMessage ;
}
