package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.enums.Icon;
import com.dsfs.dsfs.domain.repository.CustomRestaurantRepositoryImpl;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.response.*;
import com.dsfs.dsfs.global.error.exception.GeneralException;
import com.dsfs.dsfs.global.error.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CustomRestaurantRepositoryImpl customRestaurantRepositoryImpl;

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

    public RestaurantListDto getRestaurants(Long id, List<Icon> icons, int page, int size) {

        // Todo : 현재 위치에서 거리순, 현재 위치가 없다면 별점순, 별점이 동일하다면 이름 순
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "score"));
        Page<Restaurant> restaurants= customRestaurantRepositoryImpl.findRestaurantsByIcons(icons, pageRequest);
        boolean isLast = restaurants.isLast();
        int totalPage = restaurants.getTotalPages();
        long totalElement = restaurants.getTotalElements();

        List<RestaurantResponseDto> restaurantDtos = restaurants.getContent().stream()
                .map(restaurant -> RestaurantResponseDto.of(id, restaurant))
                .toList();

        return RestaurantListDto.builder()
                .isLast(isLast)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .restaurants(restaurantDtos)
                .build();
    }

    public RestaurantDetailDto getRestaurantDetail(Long id, Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        RestaurantResponseDto restaurantResponseDto = RestaurantResponseDto.of(id, restaurant);
        List<MenuListDto> menuListDtos = restaurant.getMenus().stream()
                .map(MenuListDto::of).toList();

        return RestaurantDetailDto.builder()
                .restaurant(restaurantResponseDto)
                .menus(menuListDtos)
                .build();
    }

    public void deleteRestaurant(Long id, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
        restaurantRepository.delete(restaurant);
    }
}
