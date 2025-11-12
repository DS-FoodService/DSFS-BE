package com.dsfs.dsfs.global.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpUserInfoDto {
  private String email;
  private String password;
}
