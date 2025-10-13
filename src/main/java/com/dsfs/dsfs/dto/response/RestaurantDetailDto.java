package com.dsfs.dsfs.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record RestaurantDetailDto(
        @Schema(description = "식당 정보")
        RestaurantResponseDto restaurant,
        @Schema(description = "메뉴")
        List<MenuListDto> menus
) {
}
