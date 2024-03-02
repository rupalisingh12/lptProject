package com.leanplatform.MentorshipPlatform.repositories.CustomForm;



import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormStructureRepository extends JpaRepository<FormStructure,Integer> {
    Optional<FormStructure> findByTitle(String title);
}
