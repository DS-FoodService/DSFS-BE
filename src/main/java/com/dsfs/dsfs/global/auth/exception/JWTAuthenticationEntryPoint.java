package com.dsfs.dsfs.global.auth.exception;

import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.error.exception.GeneralException;
import com.dsfs.dsfs.global.error.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    GeneralException customException = new GeneralException(ErrorStatus.INVALID_TOKEN_ERROR);
    String jsonResponse = objectMapper.writeValueAsString(customException);
    response.getWriter().write(jsonResponse);
  }
}
