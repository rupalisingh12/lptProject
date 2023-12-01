package com.leanplatform.MentorshipPlatform.dto.MenteeController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenteeAccount {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;

}
