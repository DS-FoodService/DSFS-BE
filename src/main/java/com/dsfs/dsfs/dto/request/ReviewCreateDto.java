package com.dsfs.dsfs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReviewCreateDto(
        @Schema(description = "별점")
        Float score,
        @Schema(description = "리뷰 내용")
        String content,
        @Schema(description = "레스토랑 id")
        Long restaurantId
) {
}
