package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.ServiceSessionCountDTO;
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
    @Query("SELECT DISTINCT s.menteeId FROM Session s WHERE s.createdAt BETWEEN :startDate AND :endDate")
    List<UUID> findDistinctMenteeIdsBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Query("SELECT DISTINCT s.menteeId FROM Session s")
    List<UUID> findDistinctMenteeIds();
    @Query("SELECT s.mentorId FROM Session s WHERE s.availabilityId IN :availabilityIds")
    List<UUID> findMentorIdsByAvailabilityIds(@Param("availabilityIds") List<UUID> availabilityIds);
    @Query("SELECT DISTINCT s.menteeId FROM Session s WHERE s.availabilityId IN :availabilityIds")
    List<UUID> findMenteeIdsByAvailabilityIds(@Param("availabilityIds") List<UUID> availabilityIds);
    @Query("SELECT DISTINCT s.serviceId FROM Session s WHERE s.availabilityId IN :availabilityIds")
    List<UUID> findServiceIdsByAvailabilityIds(@Param("availabilityIds") List<UUID> availabilityIds);

    @Query(
            value = "SELECT so.service_offered, COUNT(*) " +
                    "FROM Availability av " +
                    "JOIN Session s ON av.availability_id = s.availability_id " +
                    "JOIN Services_Offered so ON s.service_id = so.service_id " +
                    "WHERE av.availability_id IN :availabilityList " +
                   // "AND CAST(CONCAT(av.date, ' ', av.end_time)AS timestamp) < CURRENT_TIMESTAMP " +
                    "GROUP BY so.service_offered",
            nativeQuery = true
    )
    List<Object[]> getSessionCountsByService(@Param("availabilityList") List<UUID> availabilityList);






}






