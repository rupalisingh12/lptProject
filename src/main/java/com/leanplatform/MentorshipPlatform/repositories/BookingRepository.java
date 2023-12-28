package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByUserId(UUID userId);
    @Query("SELECT b FROM Booking b WHERE b.bookingId = :bookingId AND b.userId = :userId")
      Booking findByBookingIdAndUserId(@Param("bookingId") UUID bookingId, @Param("userId") UUID userId);
//    @Query("DELETE FROM Booking b WHERE b.userId = :userId AND b.bookingId = :bookingId")
//    void deleteByUserIdAndBookingId(@Param("bookingId") UUID bookingId, @Param("userId") UUID userId);
    Booking save (Booking booking);
    @Query("SELECT b FROM Booking b " +
            "WHERE b.startTime <= :endDateTime " +
            "AND b.endTime >= :startDateTime")
    List<Booking> findBookingsBetweenDates(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime);
    @Query("SELECT b. FROM Booking b WHERE b.scheduleId=;scheduleId AND b.date=;date")
    Booking findAllByUserIdAndDate(@Param("scheduleId") UUID scheduleId, @Param("date") LocalDate date );

}


