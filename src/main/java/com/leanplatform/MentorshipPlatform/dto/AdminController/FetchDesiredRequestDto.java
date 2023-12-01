package com.leanplatform.MentorshipPlatform.dto.AdminController;

import com.leanplatform.MentorshipPlatform.enums.MentorEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FetchDesiredRequestDto {
    private MentorEnums status;
}
