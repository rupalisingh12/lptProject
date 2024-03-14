package com.leanplatform.MentorshipPlatform.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponseObject {

    private Integer responseCode;
    private String message;
}
