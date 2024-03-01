package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddContactResponseDTO {
    private String email;
    private String details;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;

}
