package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestaurantControllerDocs {
    @Operation(summary = "식당 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 등록 성공")
    public ApiResponse<?> createRestaurant(@LoginInfo Long id, @RequestBody CreateRestaurantRequestDto req);
}
