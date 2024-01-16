package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.AvailabilityV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public  interface AvailabilityV2Repository extends JpaRepository<AvailabilityV2, UUID> {
    List<AvailabilityV2> findByScheduleId(UUID scheduleId);
    AvailabilityV2 save(AvailabilityV2 availabilityNew);
    // Example
    @Query("SELECT m FROM AvailabilityV2 m WHERE m.scheduleId = :scheduleId AND m.day = :day")
    AvailabilityV2 findByScheduleIdAndDay(@Param("scheduleId") UUID scheduleId, @Param("day") Long day);

    //  List<AvailabilityNew> findByStartTimeBetweenAndEndTimeBetween(LocalDateTime startTime,LocalDateTime endTime);
//    @Query("SELECT m. AvailabilityNew FROM AvailabilityNew m. WHERE m.scheduleId=:scheduleID AND m.day=:day ")
//    AvailabilityNew findByScheduleIdAndDay(@Param("scheduleId")UUID schduleId,@Param ("day")Long day1);
    @Query("SELECT y.day FROM AvailabilityV2 y WHERE y.scheduleId = :scheduleId")
    List<Long> findAllDayByScheduleId(@Param("scheduleId") UUID scheduleId);










}
