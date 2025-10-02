package com.dsfs.dsfs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CreateRestaurantRequestDto(
        @Schema(description = "식당 이름")
        String name,
        @Schema(description = "카카오맵 고유 장소 Id")
        Long addressId,
        @Schema(description = "도로명 주소")
        String address,
        @Schema(description = "경도, x")
        Double longitude,
        @Schema(description = "위도, y")
        Double latitude
) {
}
