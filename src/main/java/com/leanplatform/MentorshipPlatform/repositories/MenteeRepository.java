package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
