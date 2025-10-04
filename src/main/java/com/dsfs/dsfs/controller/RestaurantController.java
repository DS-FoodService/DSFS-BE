package com.dsfs.dsfs.controller;

import com.dsfs.dsfs.controller.docs.RestaurantControllerDocs;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.CreatedRestaurantDTO;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantControllerDocs {

    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<CreatedRestaurantDTO> createRestaurant(Long id, CreateRestaurantRequestDto req) {
        return ApiResponse.onSuccess(restaurantService.createRestaurant(req));
    }
}
