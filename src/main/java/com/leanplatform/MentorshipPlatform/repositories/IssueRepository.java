package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.FeedBackDetails;
import com.leanplatform.MentorshipPlatform.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
    List<Issue> findByUserName(String userName);
}
