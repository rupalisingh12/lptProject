package com.leanplatform.MentorshipPlatform.dto.AvailabilityController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityByMentor {

    private UUID mentorId;
    private Integer noOfWeeks;
    private List<ArrayList<LocalTime>> monday; //{(09:00 , 10:00),(15:00 , 20:00)}

    private List<ArrayList<LocalTime>> tuesday;

    private List<ArrayList<LocalTime>> wednesday;

    private List<ArrayList<LocalTime>> thursday;

    private List<ArrayList<LocalTime>> friday;

    private List<ArrayList<LocalTime>> saturday;

    private List<ArrayList<LocalTime>> sunday;
}
