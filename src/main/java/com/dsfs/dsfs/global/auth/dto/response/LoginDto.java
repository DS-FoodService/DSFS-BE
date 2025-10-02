package com.dsfs.dsfs.global.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginDto(
        @Schema(description = "token 값")
        String accessToken
) {
}
