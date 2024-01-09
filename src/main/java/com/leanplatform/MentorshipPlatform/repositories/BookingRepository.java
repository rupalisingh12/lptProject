package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Booking;
import com.leanplatform.MentorshipPlatform.entities.EventType;
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

    @Query("SELECT b  FROM Booking b WHERE b.bookingId = :bookingId AND b.userId = :userId")
    Booking findByBookingIdAndUserId(@Param("bookingId") UUID bookingId, @Param("userId") UUID userId);
//    @Query("SELECT e FROM EventType e WHERE e.userId = :userId AND e.eventId = :eventId")
//    EventType findByUserIdAndEventId(@Param("eventId") UUID eventId, @Param("userId") UUID userId);
//

    //    @Query("DELETE FROM Booking b WHERE b.userId = :userId AND b.bookingId = :bookingId")
//    void deleteByUserIdAndBookingId(@Param("bookingId") UUID bookingId, @Param("userId") UUID userId);
    Booking save(Booking booking);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.startTime <= :endDateTime " +
            "AND b.endTime >= :startDateTime")
    List<Booking> findBookingsBetweenDates(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime);

    @Query("SELECT b FROM Booking b WHERE b.userId = :userId AND b.date = :date")
    List<Booking> findAllByUserIdAndDate(@Param("userId") UUID userId, @Param("date") LocalDate date);

    @Query("SELECT b FROM Booking b WHERE b.userId = :userId AND b.date = :date")
    List<Booking> findByUserIdAndDate(@Param("userId") UUID userId, @Param("date") LocalDate date);
}






