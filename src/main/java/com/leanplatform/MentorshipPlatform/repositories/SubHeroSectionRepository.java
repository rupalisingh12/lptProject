package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Mentor;
import com.leanplatform.MentorshipPlatform.entities.SubHeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubHeroSectionRepository extends JpaRepository<SubHeroSection ,UUID > {

}
