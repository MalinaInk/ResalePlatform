package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.RegisterDto;

public interface RegisterService {
    /**
     * Метод для регистрации нового пользователя.
     *
     * @param registerDto данные для регистрации нового пользователя.
     * @return true, если пользователь успешно зарегистрирован, иначе - false.
     * @throws IllegalArgumentException если какое-либо поле не заполнено.
     */
    boolean register(RegisterDto registerDto);
}
