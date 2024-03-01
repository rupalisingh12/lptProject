package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.FeedBackDetails;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface FeedBackDetailsRepository  extends JpaRepository<FeedBackDetails, UUID> {
    List<FeedBackDetails> findByUserName(String userName);

}
