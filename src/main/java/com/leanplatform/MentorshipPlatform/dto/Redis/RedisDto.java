package com.leanplatform.MentorshipPlatform.dto.Redis;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisDto {

    private String otp;
    private int requestedOtpNumber;
    private int verifyOtpNumber;

    private LocalDateTime lastOtpSendAt;
    private LocalDateTime lastOtpVerifiedAt;

}
