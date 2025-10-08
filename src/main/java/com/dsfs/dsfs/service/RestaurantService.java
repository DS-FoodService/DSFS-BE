package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.enums.Icon;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.CreatedRestaurantDto;
import com.dsfs.dsfs.dto.response.RestaurantListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public CreatedRestaurantDto createRestaurant(CreateRestaurantRequestDto req) {
        Restaurant restaurant = Restaurant.builder()
                .name(req.name())
                .addressId(req.addressId())
                .address(req.address())
                .longitude(req.longitude())
                .latitude(req.latitude())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return CreatedRestaurantDto.builder()
                .restaurantId(savedRestaurant.getRestaurantId())
                .build();
    }

    public RestaurantListDto getRestaurants(Long id, Icon icon, int page, int size) {

        // 페이지 별로 조회
        // 아이콘 별 필터링
        // 즐겨찾기 여부 확인

        return null;
    }
}
