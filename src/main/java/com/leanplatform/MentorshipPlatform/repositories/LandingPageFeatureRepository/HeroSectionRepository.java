package com.leanplatform.MentorshipPlatform.repositories.LandingPageFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.LandingPage.HeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeroSectionRepository extends JpaRepository<HeroSection, UUID> {
}
