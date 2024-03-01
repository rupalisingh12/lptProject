package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.AvailabilityV2;
import com.leanplatform.MentorshipPlatform.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByUserName(String userName);




}
