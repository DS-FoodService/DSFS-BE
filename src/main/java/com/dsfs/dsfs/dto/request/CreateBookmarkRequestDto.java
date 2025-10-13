package com.dsfs.dsfs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateBookmarkRequestDto(
        @Schema(description = "즐겨찾기 할 식당 Id")
        Long restaurantId
) {
}
