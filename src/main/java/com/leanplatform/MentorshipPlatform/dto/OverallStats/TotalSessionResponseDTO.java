package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TotalSessionResponseDTO {
    private String serviceOffered;
    private Long sessionCount;



}
