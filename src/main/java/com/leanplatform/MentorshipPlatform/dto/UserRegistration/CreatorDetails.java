package com.leanplatform.MentorshipPlatform.dto.UserRegistration;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatorDetails {

    private UUID id;
    private String username;
    private String email;
    private String avatarId;
    private String phoneNo;
    private String publicUrl;

    private LocalDateTime createdAt;
}
