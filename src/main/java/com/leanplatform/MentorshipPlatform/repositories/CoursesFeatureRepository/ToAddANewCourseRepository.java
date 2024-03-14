package com.leanplatform.MentorshipPlatform.repositories.CoursesFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.ExtraDetailsOfCourses;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.ToAddANewCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToAddANewCourseRepository extends JpaRepository<ToAddANewCourse, UUID> {
    ToAddANewCourse findByUserName(String userName);



}
