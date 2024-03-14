package com.leanplatform.MentorshipPlatform.repositories.OverrideFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.OverrideAvailabilityFeature.OverrideAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OverrideAvailabilityRepository extends JpaRepository<OverrideAvailability, UUID> {
   // OverrideAvailability  findByDate(LocalDate date);
   @Query("SELECT u  FROM OverrideAvailability  u WHERE date = :date ")
   OverrideAvailability findByDate(@Param("date") LocalDate date);
   List<OverrideAvailability> findByScheduleId(UUID scheduleId);
   //findByScheduleIdAndfindByOverrideAvailabilityId(scheduleId,overrideAvailabilityId);
//   @Query(value = "SELECT u FROM  WHERE OverrideAvailability scheduleId = scheduleId AND overrideAvailabilityId = overrideAvailabilityId")
//   OverrideAvailability findByScheduleIdAndOverrideAvailabilityId(@Param(( "scheduleId")UUID scheduleId,  @Param(("overrideAvailabilityId") UUID overrideAvailabilityId);
  OverrideAvailability findByScheduleIdAndDate(UUID scheduleId, LocalDate Date);




}
