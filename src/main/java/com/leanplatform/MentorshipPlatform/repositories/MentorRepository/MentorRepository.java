package com.leanplatform.MentorshipPlatform.repositories.MentorRepository;

import com.leanplatform.MentorshipPlatform.entities.MentorEntity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface MentorRepository extends JpaRepository<Mentor, UUID> {
    Mentor save(Mentor newMentor);

    @Query("SELECT m FROM Mentor m " +
            "LEFT JOIN ServicesByMentors s ON m.mentor_id = s.mentor_id " +
            "WHERE " +
            "(:mentorRoleApplied IS NULL OR m.mentorRoleApplied = :mentorRoleApplied) AND " +
            "(:serviceNeeded IS NULL OR s.service_id = " +
            "(SELECT so.service_id FROM ServicesOffered so WHERE so.serviceOffered = :serviceNeeded)) AND " +
            "(:yearsOfExperience IS NULL OR m.yearsOfExperience >= :yearsOfExperience) AND " +
            "(:companyName IS NULL OR " +
            "m.currentPlaceOfEmployment = :companyName OR " +
            "m.lastPlaceOfEmployment = :companyName OR " +
            "m.priorToLastPlaceOfEmployment = :companyName)")
    List<Mentor> getByCriteria(
            @Param("mentorRoleApplied") String mentorRoleApplied,
            @Param("companyName") String companyName,
            @Param("serviceNeeded") String serviceNeeded,
            @Param("yearsOfExperience") Integer yearsOfExperience
    );


    @Query("SELECT m.firstName FROM Mentor m WHERE m.mentor_id = :mentorId")
    String getFirstName(UUID mentorId);

    @Query("SELECT m.lastName FROM Mentor m WHERE m.mentor_id = :mentorId")
    String getLastName(UUID mentorId);

    List<Mentor>findByCreatedAtBetween(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd);
    @Query("SELECT DISTINCT m FROM Mentor m WHERE m.mentor_id IN :mentorIds")
    List<Mentor> findDistinctMentorsByMentorIds(@Param("mentorIds") List<UUID> mentorIds);




}
