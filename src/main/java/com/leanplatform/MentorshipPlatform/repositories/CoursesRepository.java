package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, UUID> {
//   Courses findByUserName(String userName);
    Courses findByCourseId(UUID courseId);
    List<Courses> findByUserId(UUID userId);
   // List<Courses>findByUserNameAndIsEnabledOrDisabled(String userName,Boolean isEnabledOrDisabled );
    // CoursesRepository interface
   List<Courses> findByUserName(String userName);
    List<Courses> findByUserNameAndIsEnabled(String userName, Boolean isEnabled);


}
