package com.leanplatform.MentorshipPlatform.repositories.FeedBackFeatureWholeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByUserName(String userName);




}
