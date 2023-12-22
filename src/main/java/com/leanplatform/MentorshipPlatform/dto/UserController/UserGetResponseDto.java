package com.leanplatform.MentorshipPlatform.dto.UserController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserGetResponseDto {
    private String userName;
    private String fullName;
    private String email;
    private String bio;
    private String avatar;
    private UUID defaultScheduleId;
}
