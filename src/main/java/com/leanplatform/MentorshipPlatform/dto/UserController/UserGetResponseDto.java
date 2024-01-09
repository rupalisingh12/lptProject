package com.leanplatform.MentorshipPlatform.dto.UserController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserGetResponseDto {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String bio;
    private String avatar;
    private UUID defaultScheduleId;
    private LocalDateTime createdDate;
}
