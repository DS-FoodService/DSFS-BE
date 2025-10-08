package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.domain.enums.Icon;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.RestaurantDetailDto;
import com.dsfs.dsfs.dto.response.RestaurantListDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RestaurantControllerDocs {
    @Operation(summary = "식당 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 등록 성공")
    public ApiResponse<?> createRestaurant(@LoginInfo Long id, @RequestBody CreateRestaurantRequestDto req);

    @Operation(summary = "식당 목록 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 목록 조회 성공")
    public ApiResponse<RestaurantListDto> getRestaurants(
            @LoginInfo Long id,
            @RequestParam(value = "icons", required = false) List<Icon> icons,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size);

    @Operation(summary = "식당 상세 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 상세 조회 성공")
    public ApiResponse<RestaurantDetailDto> getRestaurantDetail(@LoginInfo Long id, @PathVariable Long restaurantId);

    @Operation(summary = "식당 삭제")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 삭제 성공")
    public ApiResponse<?> deleteRestaurant(@LoginInfo Long id, @PathVariable Long restaurantId);
}