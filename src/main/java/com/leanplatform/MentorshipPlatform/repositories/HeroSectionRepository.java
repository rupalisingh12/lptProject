package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Booking;
import com.leanplatform.MentorshipPlatform.entities.HeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeroSectionRepository extends JpaRepository<HeroSection, UUID> {
}
