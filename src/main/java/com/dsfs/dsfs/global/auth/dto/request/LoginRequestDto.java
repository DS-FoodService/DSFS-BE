package com.dsfs.dsfs.global.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequestDto(
	@Schema(description = "id")
	String email,
	@Schema(description = "password")
	String password
) {
}
