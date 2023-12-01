package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestObject;
import com.leanplatform.MentorshipPlatform.entities.MentorRequest;
import com.leanplatform.MentorshipPlatform.enums.MentorEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MentorRequestRepository extends JpaRepository<MentorRequest, UUID> {
    MentorRequest save(MentorRequestObject mentorRequestObject);

    @Query("SELECT m FROM MentorRequest m WHERE m.status = :status")
    List<MentorRequest> getDesiredMentorRequests(@Param("status")MentorEnums status);


    Optional<MentorRequest> findByEmail(String email);

    Optional<MentorRequest> findByPhoneNo(String phoneNo);
}
