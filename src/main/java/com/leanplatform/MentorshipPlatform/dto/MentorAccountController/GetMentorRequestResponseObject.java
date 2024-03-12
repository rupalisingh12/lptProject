package com.leanplatform.MentorshipPlatform.dto.MentorAccountController;

import com.leanplatform.MentorshipPlatform.entities.MentorEntity.MentorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetMentorRequestResponseObject {
    private String statusCode;
    private String responseMessage;
    private MentorRequest mentorRequest;
}
