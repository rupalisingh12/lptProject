package com.leanplatform.MentorshipPlatform.dto.AdminController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDecidesRequestResponse {
    private String statusCode;
    private String responseMessage ;
    private UUID uuid;
}
