package com.leanplatform.MentorshipPlatform.repositories.FeedBackFeatureWholeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.FeedBackDetails;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface FeedBackDetailsRepository  extends JpaRepository<FeedBackDetails, UUID> {
    List<FeedBackDetails> findByUserName(String userName);

}
