package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.Slot;

import java.time.LocalTime;
import java.util.List;

public interface SlotService {

    List <Slot> findSlotIdsByTimeRange(LocalTime startTime1, LocalTime endTime1);
}
