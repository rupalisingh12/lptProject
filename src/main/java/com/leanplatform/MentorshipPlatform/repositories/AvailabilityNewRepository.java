package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public  interface AvailabilityNewRepository extends JpaRepository<AvailabilityNew, UUID> {
    List<AvailabilityNew> findByScheduleId(UUID scheduleId);
    AvailabilityNew save(AvailabilityNew availabilityNew);
    // Example
    @Query("SELECT m FROM AvailabilityNew m WHERE m.scheduleId = :scheduleId AND m.day = :day")
    AvailabilityNew findByScheduleIdAndDay(@Param("scheduleId") UUID scheduleId, @Param("day") Long day);

    //  List<AvailabilityNew> findByStartTimeBetweenAndEndTimeBetween(LocalDateTime startTime,LocalDateTime endTime);
//    @Query("SELECT m. AvailabilityNew FROM AvailabilityNew m. WHERE m.scheduleId=:scheduleID AND m.day=:day ")
//    AvailabilityNew findByScheduleIdAndDay(@Param("scheduleId")UUID schduleId,@Param ("day")Long day1);
    @Query("SELECT y.day FROM AvailabilityNew y WHERE y.scheduleId = :scheduleId")
    List<Long> findAllDayByScheduleId(@Param("scheduleId") UUID scheduleId);










}
