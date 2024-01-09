package com.leanplatform.MentorshipPlatform.dto.AvailabilityNew;

import com.leanplatform.MentorshipPlatform.mappers.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAvailabilityNewRequest {
    List<Slot>mon;
    List<Slot>tue;
    List<Slot>wed;
    List<Slot>thur;
    List<Slot>fri;
    List<Slot>sat;
    List<Slot>sun;



}
