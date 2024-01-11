package com.leanplatform.MentorshipPlatform.dto.AvailabilityNew;

import com.leanplatform.MentorshipPlatform.mappers.Slot;
import com.leanplatform.MentorshipPlatform.mappers.SlotTimeDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllAvailabilitiesResponse {
    private String statusCode;
    private String responseMessage;
    private List<SlotTimeDate> dateRanges;
    private List<Day>workingHours;
}
