package com.leanplatform.MentorshipPlatform.dto.UserRegistration;



import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObj {

    private String message;
//    private List<ContentResponseDto> cards;
//
//    private List<Answer> answers;
    private CreatorDetails creatorDetails;

    private int responseCode;
    private int isCampusUser;

    private String token;
}
