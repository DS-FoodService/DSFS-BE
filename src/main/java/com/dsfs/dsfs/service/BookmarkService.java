package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Bookmark;
import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.User;
import com.dsfs.dsfs.domain.repository.BookmarkRepository;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.domain.repository.UserRepository;
import com.dsfs.dsfs.dto.request.CreateBookmarkRequestDto;
import com.dsfs.dsfs.dto.response.CreatedBookmarkDto;
import com.dsfs.dsfs.global.error.exception.GeneralException;
import com.dsfs.dsfs.global.error.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
