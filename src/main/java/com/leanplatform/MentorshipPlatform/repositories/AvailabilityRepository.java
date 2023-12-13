package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Availability;
import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {
    Availability save(Availability availability);

    @Query("SELECT a FROM Availability a WHERE a.mentorId = :mentorId AND a.isBooked = false")
    List<Availability> getDesiredMentorAvailability(@Param("mentorId") UUID mentorId);

    @Query("SELECT a.startTime FROM Availability a WHERE a.availabilityId = :availabilityId")
    LocalTime getStartTimeOfSlot(UUID availabilityId);

    @Query("SELECT a.endTime FROM Availability a WHERE a.availabilityId = :availabilityId")
    LocalTime getEndTimeOfSlot(UUID availabilityId);
    @Query("SELECT a.date FROM Availability a WHERE a.availabilityId = :availabilityId")
    LocalDate getSlotDate(UUID availabilityId);
    @Query("SELECT a.dayOfTheWeek FROM Availability a WHERE a.availabilityId = :availabilityId")
    DaysOfTheWeek getDayOfTheWeek(UUID availabilityId);
    @Query("SELECT a.availabilityId FROM Availability a WHERE date(CONCAT(a.date, ' ', a.endTime)) < CURRENT_TIMESTAMP")
    List<UUID> findAvailabilityIdsBeforeCurrentTime();
//
@Query(value = "SELECT a.availability_id FROM availability a WHERE a.date = CURRENT_DATE - INTERVAL '1 day'", nativeQuery = true)
List<UUID> findAvailabilityIdsYesterday();
    @Query(value = "SELECT a.availability_id FROM availability a WHERE a.date >= CURRENT_DATE - INTERVAL '7 days' AND a.date < CURRENT_DATE", nativeQuery = true)
    List<UUID>findAvailabilityIdsInPreviousWeek();








}
