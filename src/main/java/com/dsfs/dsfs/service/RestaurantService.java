package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.CreatedRestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public CreatedRestaurantDTO createRestaurant(CreateRestaurantRequestDto req) {
        Restaurant restaurant = Restaurant.builder()
                .name(req.name())
                .addressId(req.addressId())
                .address(req.address())
                .longitude(req.longitude())
                .latitude(req.latitude())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return CreatedRestaurantDTO.builder()
                .restaurantId(savedRestaurant.getRestaurantId())
                .build();
    }
}
