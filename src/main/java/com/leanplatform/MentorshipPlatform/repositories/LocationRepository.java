package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.dto.BookingController.LocationDTO;
import com.leanplatform.MentorshipPlatform.entities.Location;
import com.leanplatform.MentorshipPlatform.entities.ServicesByMentors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    UUID save(LocationDTO location);
}
