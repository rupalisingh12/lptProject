package com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository;

import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {

       List<Attendee> findByBookingId(UUID bookingId);


}
