package com.dsfs.dsfs.global.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpUserInfoDto {
  private Long userid;
  private String email;
  private String password;
}
