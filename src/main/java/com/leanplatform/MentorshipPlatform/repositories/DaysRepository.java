package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;
@Repository
public interface DaysRepository extends JpaRepository<Days, UUID> {
     Days save(Days days);
//    @Query("SELECT d FROM Days d WHERE d.availabilityNew.availabilityId = :availabilityId")
//    List<Days> findAllDaysByAvailabilityId(@Param("availabilityId") Long availabilityId);
@Query("SELECT DISTINCT d.day FROM Days d WHERE d.scheduleId = :scheduleId")
List<Long> findDistinctDaysByScheduleId(@Param("scheduleId") UUID scheduleId);
}
