package com.leanplatform.MentorshipPlatform.dto.MentorAccountController;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MentorUpdateRequestObject {
    private String resume;
    //multipart resume
    private String email;
    private String linkedIn_link;
    private Integer yearsOfExperience;
    private String mentorRoleApplied;
}
