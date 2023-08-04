package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.RegisterDto;
import com.malina_ink.resaleplatform.exception.UserAlreadyExistException;
import com.malina_ink.resaleplatform.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RegisterServiceImpl implements RegisterService {
    private final UserDetailsManagerImpl userDetailsManager;

    @Override
    public boolean register(RegisterDto registerDto) {
        if (registerDto.getUsername().isBlank() || registerDto.getFirstName().isBlank()
                || registerDto.getLastName().isBlank() || registerDto.getPhone().isBlank()
                || registerDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("All fields must be filled");
        }
        try {
            userDetailsManager.createUser(registerDto);
        } catch (UserAlreadyExistException e) {
            log.error(e.getMessage());
        }
        log.info("User {} successfully created", registerDto.getUsername());
        return true;
    }
}
