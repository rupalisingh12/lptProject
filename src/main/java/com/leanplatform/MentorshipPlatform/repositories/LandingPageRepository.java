package com.leanplatform.MentorshipPlatform.repositories;
import com.leanplatform.MentorshipPlatform.entities.LandingPage;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LandingPageRepository extends JpaRepository<LandingPage ,UUID> {
}
