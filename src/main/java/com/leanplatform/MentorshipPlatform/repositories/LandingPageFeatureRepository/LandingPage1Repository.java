package com.leanplatform.MentorshipPlatform.repositories.LandingPageFeatureRepository;
import com.leanplatform.MentorshipPlatform.entities.LandingPage.LandingPage1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LandingPage1Repository extends JpaRepository<LandingPage1,UUID> {
    LandingPage1 findByUserName(String userName);
}
