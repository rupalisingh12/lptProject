package com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Day {
    private List<Long> days;
}