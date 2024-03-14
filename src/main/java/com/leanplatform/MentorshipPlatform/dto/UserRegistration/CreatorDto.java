package com.leanplatform.MentorshipPlatform.dto.UserRegistration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatorDto {

    private String fullName;

    private String phoneNo;

    private String otp;

    private String emailId;

    private String pin;

    private Integer isCampusUser;

    private String avatarId;
}
