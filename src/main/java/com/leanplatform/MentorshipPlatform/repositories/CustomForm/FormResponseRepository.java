package com.leanplatform.MentorshipPlatform.repositories.CustomForm;



import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FormResponseRepository extends JpaRepository<FormResponse,Integer> {
    Optional<FormResponse> findByTitleAndVisitorId(String title, UUID visitorId);
}
