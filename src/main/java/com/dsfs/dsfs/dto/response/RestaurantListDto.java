package com.dsfs.dsfs.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RestaurantListDto(
        boolean isLast,
        int totalPage,
        long totalElement,
        List<RestaurantResponseDto> restaurants
) {
}
