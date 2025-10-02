package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void createRestaurant(CreateRestaurantRequestDto req) {
        Restaurant restaurant = Restaurant.builder()
                .name(req.name())
                .addressId(req.addressId())
                .address(req.address())
                .longitude(req.longitude())
                .latitude(req.latitude())
                .build();

        restaurantRepository.save(restaurant);
    }
}
