package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventTypesRepository extends JpaRepository<EventType, UUID> {
    EventType save( EventType eventType);
    @Query("SELECT e FROM EventType e WHERE e.userId = :userId")
    List<EventType> findAllByUserId(@Param("userId")UUID userId);
    @Query("SELECT e FROM EventType e WHERE e.userId = :userId AND e.eventId = :eventId")
    EventType findByUserIdAndEventId( @Param("eventId") UUID eventId,@Param("userId") UUID userId);
    @Modifying
    @Query("DELETE FROM EventType e WHERE e.userId = :userId AND e.eventId = :eventId")
    void deleteByUserIdAndEventId(@Param("eventId") UUID eventId, @Param("userId") UUID userId);
    @Query("SELECT e.length FROM EventType e WHERE e.eventId = :eventId")
    Integer findEventTypeLengthByEventId(@Param("eventId") UUID eventId);
    @Query("SELECT e.scheduleId FROM EventType e WHERE e.eventId=:eventId")
    UUID findScheduleIdByEventTypeId(@Param("eventId")UUID eventId);
   // EventType findByEventTypeId(UUID eventTypeId);
}

