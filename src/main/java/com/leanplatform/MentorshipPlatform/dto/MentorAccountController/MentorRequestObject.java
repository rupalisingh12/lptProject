package com.leanplatform.MentorshipPlatform.dto.MentorAccountController;

import jakarta.persistence.*;
import lombok.*;


@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MentorRequestObject {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
}
