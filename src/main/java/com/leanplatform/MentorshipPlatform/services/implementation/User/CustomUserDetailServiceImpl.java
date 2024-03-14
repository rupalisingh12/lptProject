package com.leanplatform.MentorshipPlatform.services.implementation.User;



import com.leanplatform.MentorshipPlatform.entities.User.Creator;
import com.leanplatform.MentorshipPlatform.entities.User.Role;
import com.leanplatform.MentorshipPlatform.repositories.User.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    CreatorRepository creatorRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {

        Creator creator = creatorRepository.findByEmail(phoneNo);

        if(creator !=null){
            return new org.springframework.security.core.userdetails.User(creator.getEmail(), creator.getPin(),mapRolesToAuthorities(creator.getRoles()));
        }
        else{
            throw new UsernameNotFoundException("Invalid Email or password Error");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection< ? extends GrantedAuthority> mapRoles=roles.stream()
                .map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return mapRoles;
    }
}
