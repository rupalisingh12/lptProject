package com.leanplatform.MentorshipPlatform.repositories.LandingPageFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.LandingPage.CreatorFeatureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreatorFeatureInfoRepository extends JpaRepository<CreatorFeatureInfo, UUID> {
    CreatorFeatureInfo findByUserName(String userName);
}
