package com.leanplatform.MentorshipPlatform.dto.ScheduleController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.UpdateAvailabilityNewResponseDTO;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteScheduleDTO {
    private UUID id;
    private UUID userId;
    private String name;

  private UpdateAvailabilityNewResponseDTO availability;



}
