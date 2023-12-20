package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByUserId(UUID userId);
  //  List<Booking> findByBookingIdAndUserId(UUID userId);
//    @Query("DELETE FROM Booking b WHERE b.userId = :userId AND b.bookingId = :bookingId")
//    void deleteByUserIdAndBookingId(@Param("bookingId") UUID bookingId, @Param("userId") UUID userId);
    Booking save (Booking booking);



}
