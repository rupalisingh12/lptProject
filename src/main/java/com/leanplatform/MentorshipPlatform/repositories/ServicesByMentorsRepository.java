package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.ServicesByMentors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServicesByMentorsRepository extends JpaRepository<ServicesByMentors , UUID> {
    ServicesByMentors save(ServicesByMentors servicesByMentors);

    @Query("SELECT s FROM ServicesByMentors s WHERE s.mentor_id = :mentorId")
    List<ServicesByMentors> getDesiredMentorServices(@Param("mentorId") UUID mentorId);

}
