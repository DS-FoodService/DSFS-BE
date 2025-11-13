package com.dsfs.dsfs.dto.response;

import com.dsfs.dsfs.domain.Bookmark;
import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.Review;
import com.dsfs.dsfs.domain.enums.CampusType;
import com.dsfs.dsfs.domain.enums.Icon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
public record RestaurantResponseDto(
        @Schema(description = "즐겨찾기 여부")
        Boolean isBookmarked,
        @Schema(description = "즐겨찾기 id")
        Long bookmarkId,
        @Schema(description = "식당 id")
        Long restaurantId,
        @Schema(description = "식당 이름")
        String name,
        @Schema(description = "식당 타입")
        CampusType campusType,
        @Schema(description = "장소 고유 id")
        Long addressId,
        @Schema(description = "경도, x")
        Double longitude,
        @Schema(description = "위도, y")
        Double latitude,
        @Schema(description = "별점")
        Double score,
        @Schema(description = "리뷰 갯수")
        Integer reviewCount,
        @Schema(description = "아이콘 리스트")
        List<Icon> icons
) {
        public static RestaurantResponseDto of(Long userId, Restaurant restaurant) {

                Optional<Long> bookmarkIdOpt = Optional.ofNullable(userId)
                        .flatMap(id -> restaurant.getBookmarks().stream()
                                .filter(b -> b.getUser().getUserId().equals(id))
                                .map(Bookmark::getBookmarkId)
                                .findFirst());

                Boolean isBookmarked = bookmarkIdOpt.isPresent();
                Long bookmarkId = bookmarkIdOpt.orElse(null);

                Double averageScore = restaurant.getReviews().isEmpty()
                        ? 0.0
                        : restaurant.getReviews().stream()
                        .mapToDouble(Review::getScore)
                        .average()
                        .orElse(0.0);

                List<Icon> icons = restaurant.getMenus().stream()
                        .flatMap(menu -> menu.getIcons().stream())
                        .distinct()
                        .collect(Collectors.toList());

                return RestaurantResponseDto.builder()
                        .isBookmarked(isBookmarked)
                        .bookmarkId(bookmarkId)
                        .restaurantId(restaurant.getRestaurantId())
                        .name(restaurant.getName())
                        .campusType(restaurant.getCampusType())
                        .addressId(restaurant.getAddressId())
                        .longitude(restaurant.getLongitude())
                        .latitude(restaurant.getLatitude())
                        .score(averageScore)
                        .reviewCount(restaurant.getReviews().size())
                        .icons(icons)
                        .build();
        }
}
