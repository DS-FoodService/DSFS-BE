package com.dsfs.dsfs.controller;

import com.dsfs.dsfs.controller.docs.RestaurantControllerDocs;
import com.dsfs.dsfs.domain.enums.Icon;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.CreatedRestaurantDto;
import com.dsfs.dsfs.dto.response.RestaurantListDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantControllerDocs {

    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<CreatedRestaurantDto> createRestaurant(Long id, CreateRestaurantRequestDto req) {
        return ApiResponse.onSuccess(restaurantService.createRestaurant(req));
    }

    @GetMapping
    public ApiResponse<RestaurantListDto> getRestaurants(Long id, Icon icon, int page, int size) {
        return ApiResponse.onSuccess(restaurantService.getRestaurants(id, icon, page, size));
    }
}
