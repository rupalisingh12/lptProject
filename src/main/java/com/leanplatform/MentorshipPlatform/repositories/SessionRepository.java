package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Query("SELECT s.availabilityId FROM Session s WHERE s.sessionId = :sessionId")
    UUID getAvailabilityId(@Param("sessionId") UUID sessionId);

    @Query("SELECT COUNT(s) FROM Session s WHERE s.mentorId = :mentorId")
    int getSessionCountOfMentor(@Param("mentorId") UUID mentorId);

    @Query("SELECT s FROM Session s WHERE s.menteeId = :menteeId")
    List<Session> findByMentee(@Param("menteeId") UUID menteeId);
    @Query("SELECT DISTINCT s.mentorId FROM Session s")
    List<UUID> findDistinctMentorIds();
    @Query("SELECT DISTINCT s.mentorId FROM Session s WHERE s.createdAt BETWEEN :startDate AND :endDate")
    List<UUID> findDistinctMentorIdsBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}

