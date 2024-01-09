package com.leanplatform.MentorshipPlatform.dto.ScheduleController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.UpdateAvailabilityNewResponseDTO;

import java.util.List;
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
