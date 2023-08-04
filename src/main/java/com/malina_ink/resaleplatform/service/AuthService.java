package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.RegisterDto;
import com.malina_ink.resaleplatform.enums.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterDto registerReq, Role role);
}
