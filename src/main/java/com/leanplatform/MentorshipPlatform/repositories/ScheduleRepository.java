package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Booking;
import com.leanplatform.MentorshipPlatform.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleRepository  extends JpaRepository<Schedule, UUID> {
    Schedule save (Schedule schedule);
    List< Schedule> findByUserId(UUID userId);
    Schedule findByScheduleIdAndUserId(UUID scheduleId, UUID userId);
//    @Query("SELECT m.userId FROM Schedule m. WHERE m.scheduleId=:scheduleId")
//    UUID findByScheduleId(UUID scheduleId1);
     Schedule findByScheduleId(UUID scheduleId);


}
