package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.RegisterDto;
import com.malina_ink.resaleplatform.dto.UserDetailsDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.exception.UserAlreadyExistException;
import com.malina_ink.resaleplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserDetailsManagerImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserDetailsManagerImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
    public void createUser(RegisterDto registerDto) {
        if (userRepository.existsByEmailIgnoreCase(registerDto.getUsername())) {
            throw new UserAlreadyExistException("User already exist");
        }
        User user = new User();
        user.setUsername(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getUsername());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setPhone(registerDto.getPhone());
        user.setRole(registerDto.getRole());

        userRepository.save(user);
        log.debug("User successfully created = {}", user);
    }


}
