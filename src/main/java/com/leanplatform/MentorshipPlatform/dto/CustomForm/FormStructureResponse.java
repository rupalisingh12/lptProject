package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormStructureResponse {

    private int responseCode;
    private String message;
    private String form_Url;
}
