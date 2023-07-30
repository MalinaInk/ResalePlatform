package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.RegisterDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.enums.Role;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.AuthService;
import com.malina_ink.resaleplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
    if (manager.loadUserByUsername(registerDto.getUsername()) != null) {
      return false;
    }
    User user = new User();
    user.setRole(registerDto.getRole());
    user.setEmail(registerDto.getUsername());
    user.setPassword(registerDto.getPassword());
    user.setPhone(registerDto.getPhone());
    user.setFirstname(registerDto.getFirstName());
    user.setLastName(registerDto.getLastName());
    user.setUsername(registerDto.getUsername());
    userRepository.save(user);
    return true;
  }
}
