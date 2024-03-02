package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitFormRequestObject {

    @NotNull(message = "response Id cannot be null")
    private int responseId;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull (message = "email cannot be null")
    private String email;
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    private String others;
}
