package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.CreatorCustomDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreatorCustomDomainRepository extends JpaRepository<CreatorCustomDomain,Long> {

    @Query("SELECT c FROM CreatorCustomDomain c WHERE c.domain=:username")
    CreatorCustomDomain getByDomain(@Param("username") String userName);
}
