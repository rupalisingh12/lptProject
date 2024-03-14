package com.leanplatform.MentorshipPlatform.dto.UserRegistration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PinDto {

    private String oldPin;
    private String newPin;
    private String phoneNo;
}
