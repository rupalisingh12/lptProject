package com.leanplatform.MentorshipPlatform.dto.UserRegistration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatorRequest {

    String username;
    String password;
}
