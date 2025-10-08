package com.dsfs.dsfs.dto.response;

import com.dsfs.dsfs.domain.enums.Icon;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record RestaurantResponseDto(
        @Schema(description = "즐겨찾기 여부")
        Boolean isBookmarked,
        @Schema(description = "식당 id")
        Long restaurantId,
        @Schema(description = "식당 이름")
        String name,
        @Schema(description = "장소 고유 id")
        Long addressId,
        @Schema(description = "경도, x")
        Double longitude,
        @Schema(description = "위도, y")
        Double latitude,
        @Schema(description = "별점")
        Double score,
        @Schema(description = "리뷰 갯수")
        Integer reviewCount,
        @Schema(description = "아이콘 리스트")
        List<Icon> icons
) {
}
