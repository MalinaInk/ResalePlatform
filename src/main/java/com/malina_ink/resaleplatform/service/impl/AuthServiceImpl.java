package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.RegisterDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.enums.Role;
import com.malina_ink.resaleplatform.exception.UserAlreadyExistException;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.AuthService;
import com.malina_ink.resaleplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManager manager;

  private final PasswordEncoder encoder;

  private final UserRepository userRepository;


  @Override
  public boolean login(String userName, String password) {
    if (!manager.userExists(userName)) {
      return false;
    }
    UserDetails userDetails = manager.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterDto registerDto, Role role) {
//    if (manager.userExists(registerDto.getUsername())) {
//      return false;
//    }
//    manager.createUser(
//        User.builder()
//            .passwordEncoder(this.encoder::encode)
//            .password(registerDto.getPassword())
//            .username(registerDto.getUsername())
//            .roles(role.name())
//
    if (manager.userExists(registerDto.getUsername())) {
      return false;
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
    return true;
  }
}
