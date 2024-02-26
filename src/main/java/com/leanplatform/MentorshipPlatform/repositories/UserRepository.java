package com.leanplatform.MentorshipPlatform.repositories;

import com.leanplatform.MentorshipPlatform.entities.UserEntity;
import com.twilio.rest.chat.v1.service.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT u FROM UserEntity u WHERE u.userId = :userId")
    UserEntity findByUserId(@Param("userId")  UUID userId);
//    @Query("SELECT m FROM UserEntity m WHERE m.userName = :userName")
//    UserEntity findByUserName(@Param("userName")String userName);
        UserEntity findByUserName(String userName);



}

