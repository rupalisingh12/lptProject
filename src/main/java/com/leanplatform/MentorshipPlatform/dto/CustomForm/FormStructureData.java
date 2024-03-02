package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormStructureData {

    private int responseId;
    private String title;
    private String name;
    private String email;
    private String phoneNumber;
    private String posterUrl;
    private String description;
    private boolean isExperience;
    private String others;
    private String navigateTo;
    private UUID visitorId;

}
