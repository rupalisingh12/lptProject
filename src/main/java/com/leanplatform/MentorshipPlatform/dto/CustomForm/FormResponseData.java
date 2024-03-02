package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormResponseData {

    private int responseCode;
    private String message;
    private FormStructureData formStructureData;

}
