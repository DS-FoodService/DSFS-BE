package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.domain.enums.Icon;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.RestaurantListDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RestaurantControllerDocs {
    @Operation(summary = "식당 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 등록 성공")
    public ApiResponse<?> createRestaurant(@LoginInfo Long id, @RequestBody CreateRestaurantRequestDto req);


    @Operation(description = "상품 목록을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 목록 조회 성공")
    public ApiResponse<RestaurantListDto> getRestaurants(
            @LoginInfo Long id,
            @RequestParam(value = "icons") List<Icon> icons,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size);
}