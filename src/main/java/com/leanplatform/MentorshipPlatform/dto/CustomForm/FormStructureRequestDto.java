package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormStructureRequestDto {

    private UUID visitorId;
    private String title;
}
