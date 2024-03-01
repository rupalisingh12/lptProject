package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Issue;
import com.leanplatform.MentorshipPlatform.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
   List<Rating> findByUserName(String userName);



}
