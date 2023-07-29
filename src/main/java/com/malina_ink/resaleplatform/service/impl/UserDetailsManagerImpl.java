package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.UserDetailsDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsManagerImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmailIgnoreCase(username).orElseThrow();
        UserDetailsDto userDetailsDto = new UserDetailsDto(
                user.getUsername(),
                user.getPassword(),
                user.getId(),
                user.getRole()
        );
        return new UserPrincipal(userDetailsDto);
    }


}
