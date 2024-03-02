package com.leanplatform.MentorshipPlatform.dto.CustomForm;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormStructureDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String others;
    private MultipartFile poster;
    private String description;
    private String navigateTo;

}
