package com.leanplatform.MentorshipPlatform.dto;

import com.leanplatform.MentorshipPlatform.enums.OtpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpResponseDto {
    private OtpStatus status;
    private String message;
}
