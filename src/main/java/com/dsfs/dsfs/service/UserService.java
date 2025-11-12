package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.User;
import com.dsfs.dsfs.domain.repository.UserRepository;
import com.dsfs.dsfs.global.auth.dto.request.LoginRequestDto;
import com.dsfs.dsfs.global.auth.dto.response.SignUpUserInfoDto;
import com.dsfs.dsfs.global.error.exception.GeneralException;
import com.dsfs.dsfs.global.error.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpUserInfoDto signUpUserInfoDto) {
        User user = User.builder()
                .email(signUpUserInfoDto.getEmail())
                .password(passwordEncoder.encode(signUpUserInfoDto.getPassword()))
                .build();

        try {
            userRepository.save(user);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("try to save duplicated user");
        }
    }

    public User getUser(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email());
        if (user == null) {
            throw new GeneralException(ErrorStatus.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.WRONG_PASSWORD);
        }
        return user;
    }
}
