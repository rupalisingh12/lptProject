package com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository;

import com.leanplatform.MentorshipPlatform.dto.BookingController.LocationDTO;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    UUID save(LocationDTO location);
}
