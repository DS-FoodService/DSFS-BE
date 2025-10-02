package com.dsfs.dsfs.global.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpRequestDto(
	@Schema(description = "email")
	String email,
	@Schema(description = "비밀번호")
	String password
) {
}
