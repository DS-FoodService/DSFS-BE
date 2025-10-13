package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.Review;
import com.dsfs.dsfs.domain.User;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.domain.repository.ReviewRepository;
import com.dsfs.dsfs.domain.repository.UserRepository;
import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.dto.response.CreatedReviewDto;
import com.dsfs.dsfs.global.error.exception.GeneralException;
import com.dsfs.dsfs.global.error.status.ErrorStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    public CreatedReviewDto createReview(Long id, ReviewCreateDto req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.restaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Review review = Review.builder()
                .score(req.score())
                .content(req.content())
                .user(user)
                .restaurant(restaurant)
                .build();

        Review savedReview = reviewRepository.save(review);
        return CreatedReviewDto.builder()
                .reviewId(savedReview.getReviewId())
                .build();
    }
}
