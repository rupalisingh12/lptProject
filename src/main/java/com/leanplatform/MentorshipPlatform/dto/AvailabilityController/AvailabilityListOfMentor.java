package com.leanplatform.MentorshipPlatform.dto.AvailabilityController;

import com.leanplatform.MentorshipPlatform.entities.AvailabiliyFeature.Availability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityListOfMentor {
    private String statusCode;
    private String responseMessage;
    private List<Availability> availabilityList;
}
