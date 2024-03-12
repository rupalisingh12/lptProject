package com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository;

import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee , UUID> {

    @Query("SELECT m.firstName FROM Mentee m WHERE m.menteeId = :menteeId")
    String getFirstName(UUID menteeId);

    @Query("SELECT m.lastName FROM Mentee m WHERE m.menteeId = :menteeId")
    String getLastName(UUID menteeId);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhoneNo(String phoneNo);
    @Query("SELECT m FROM Mentee m WHERE m.menteeId IN :menteeIds")
    List<Mentee> findMenteesByMenteeIds(@Param("menteeIds") List<UUID> menteeIds);
    List<Mentee>findByCreatedAtBetween(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd);
    @Query("SELECT DISTINCT m FROM Mentee m WHERE m.menteeId IN :menteeIds")
    List<Mentee> findDistinctMenteesByMenteeIds(@Param("menteeIds") List<UUID> menteeIds);



}
