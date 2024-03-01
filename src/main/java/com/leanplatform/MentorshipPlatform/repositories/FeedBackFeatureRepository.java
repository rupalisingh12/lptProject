package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.EventType;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedBackFeatureRepository extends JpaRepository<FeedBackFeature, UUID> {
    List<FeedBackFeature> findByUserName(String userName);
    FeedBackFeature findByUserNameAndFormType(String userEntity,String formType);
}
