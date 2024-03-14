package com.leanplatform.MentorshipPlatform.repositories.FeedBackFeatureWholeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.Suggestion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, UUID> {
  List<Suggestion> findByUserName(String userName);
}
