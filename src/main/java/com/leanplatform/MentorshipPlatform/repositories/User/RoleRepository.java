package com.leanplatform.MentorshipPlatform.repositories.User;


import com.leanplatform.MentorshipPlatform.entities.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {

     Role findByName(String name);

     @Query("SELECT r FROM Role r WHERE r.name=:name")
     Role getByName(String name);
}
