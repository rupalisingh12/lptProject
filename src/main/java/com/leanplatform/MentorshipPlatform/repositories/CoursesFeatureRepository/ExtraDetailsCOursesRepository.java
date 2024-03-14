package com.leanplatform.MentorshipPlatform.repositories.CoursesFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.ExtraDetailsOfCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExtraDetailsCOursesRepository extends JpaRepository<ExtraDetailsOfCourses, UUID> {
  ExtraDetailsOfCourses findByCourseId(UUID  courseId);

}
