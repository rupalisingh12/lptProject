package com.leanplatform.MentorshipPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Set;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
//    @Query("SELECT m.slotIds FROM slot m WHERE  m.startTime>=:startTime and m.endTime=<:endTime")
//    List<Long> findSlotIdsBetweenStartTimeAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
@Query("SELECT s.slotId FROM Slot s WHERE s.startTime >= :startTime AND s.endTime <= :endTime")
Set<Long> findSlotIdsByTimeRange(@Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);



}
