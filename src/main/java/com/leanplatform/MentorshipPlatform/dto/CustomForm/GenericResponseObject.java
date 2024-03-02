package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseObject {
    private String message;

    private int responseCode;
}
