package com.dsfs.dsfs.dto.response;

import java.util.List;

public record RestaurantListDto(
        boolean isLast,
        int totalPage,
        long totalElement,
        List<RestaurantResponseDto> products
) {
}
