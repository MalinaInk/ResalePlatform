package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.RegisterDto;
import com.malina_ink.resaleplatform.dto.UserDetailsDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.exception.UserAlreadyExistException;
import com.malina_ink.resaleplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserDetailsManagerImpl implements UserDetailsService, UserDetailsManager {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmailIgnoreCase(username).orElseThrow();
        UserDetailsDto userDetailsDto = new UserDetailsDto(
                user.getEmail(),
                user.getPassword(),
                user.getId(),
                user.getRole(),
                user.getUsername()
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


    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.getUserByEmailIgnoreCase(username).isPresent();
    }

}
