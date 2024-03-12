package com.leanplatform.MentorshipPlatform.repositories.LandingPageFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.LandingPage.LandingPage2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LandingPage2Repository extends JpaRepository<LandingPage2, UUID> {
    LandingPage2 findByUserName(String userName);
}
