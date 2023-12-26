package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public  interface AvailabilityNewRepository extends JpaRepository<AvailabilityNew, UUID> {
    List<AvailabilityNew> findByScheduleId( UUID scheduleId);
    AvailabilityNew save(AvailabilityNew availabilityNew);
  //  List<AvailabilityNew> findByStartTimeBetweenAndEndTimeBetween(LocalDateTime startTime,LocalDateTime endTime);


}
