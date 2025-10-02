package com.dsfs.dsfs.global.auth.controller;

import com.dsfs.dsfs.domain.User;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.dto.request.LoginRequestDto;
import com.dsfs.dsfs.global.auth.dto.request.SignUpRequestDto;
import com.dsfs.dsfs.global.auth.dto.response.LoginDto;
import com.dsfs.dsfs.global.auth.dto.response.SignUpUserInfoDto;
import com.dsfs.dsfs.global.auth.jwt.JWTUtil;
import com.dsfs.dsfs.global.error.status.SuccessStatus;
import com.dsfs.dsfs.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

  private final UserService userService;
  private final JWTUtil jwtUtil;

  @PostMapping("/login")
  public ApiResponse<LoginDto> login(LoginRequestDto loginRequestDto, HttpServletRequest request, HttpServletResponse response) {
      User user = userService.getUser(loginRequestDto);
      String jwtToken = jwtUtil.createJwt(user.getUserId(), 60 * 60 * 60 * 1000L);

      LoginDto loginDto = LoginDto.builder()
              .accessToken(jwtToken).build();

      return ApiResponse.onSuccess(loginDto);
  }

  @PostMapping("/signup")
  public ApiResponse<?> signup(SignUpRequestDto signUpRequestDto) {
      SignUpUserInfoDto userInfoDto = SignUpUserInfoDto.builder()
              .email(signUpRequestDto.email())
              .password(signUpRequestDto.password())
              .build();

      userService.signUp(userInfoDto);

      return ApiResponse.onSuccess(SuccessStatus._OK);
  }
}
