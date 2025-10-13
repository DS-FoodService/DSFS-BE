package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Bookmark;
import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.User;
import com.dsfs.dsfs.domain.repository.BookmarkRepository;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.domain.repository.UserRepository;
import com.dsfs.dsfs.dto.request.CreateBookmarkRequestDto;
import com.dsfs.dsfs.dto.response.CreatedBookmarkDto;
import com.dsfs.dsfs.dto.response.RestaurantListDto;
import com.dsfs.dsfs.dto.response.RestaurantResponseDto;
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
public class BookmarkService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final BookmarkRepository bookmarkRepository;

    public CreatedBookmarkDto createBookmark(Long id, CreateBookmarkRequestDto req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(req.restaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .restaurant(restaurant)
                .build();

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return CreatedBookmarkDto.builder()
                .bookmarkId(savedBookmark.getBookmarkId())
                .build();
    }

    public RestaurantListDto getBookmarks(Long id, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(user);
        List<Long> restaurantIds = bookmarks.stream()
                .map(bookmark -> bookmark.getRestaurant().getRestaurantId()).toList();
        Page<Restaurant> restaurants = restaurantRepository.findByRestaurantIdIn(restaurantIds, pageRequest);

        List<RestaurantResponseDto> restaurantResponseDtos = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.of(id, restaurant)).toList();

        return RestaurantListDto.builder()
                .isLast(restaurants.isLast())
                .totalPage(restaurants.getTotalPages())
                .totalElement(restaurants.getTotalElements())
                .restaurants(restaurantResponseDtos)
                .build();
    }
}
