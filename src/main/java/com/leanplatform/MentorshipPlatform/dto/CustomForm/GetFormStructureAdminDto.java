package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFormStructureAdminDto {
    private int responseCode;
    private String message;
    private String title;
    private String name;
    private String phoneNumber;
    private String email;
    private String others;
    private String description;
    private  String posterUrl;
    private boolean isExperience;
    private String formUrl;
    private String navigateTo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private long totalOpens;
    private long totalResponses;
}
