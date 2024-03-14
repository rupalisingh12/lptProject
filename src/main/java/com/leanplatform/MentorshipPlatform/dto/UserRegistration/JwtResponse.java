package com.leanplatform.MentorshipPlatform.dto.UserRegistration;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    int responseCode;
    String token;
    String message;
    CreatorDetails creatorDetails;
}
