package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.ServicesOffered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicesOfferedRepository extends JpaRepository<ServicesOffered, UUID> {
    ServicesOffered findByServiceOffered(String serviceOffered);

    @Query("SELECT so.serviceOffered FROM ServicesOffered so WHERE so.service_id = :serviceId")
    String getServiceOffered(UUID serviceId);
}

