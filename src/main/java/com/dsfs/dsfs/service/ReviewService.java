package com.dsfs.dsfs.service;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.Review;
import com.dsfs.dsfs.domain.User;
import com.dsfs.dsfs.domain.repository.RestaurantRepository;
import com.dsfs.dsfs.domain.repository.ReviewRepository;
import com.dsfs.dsfs.domain.repository.UserRepository;
import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.dto.response.CreatedReviewDto;
import com.dsfs.dsfs.dto.response.ReviewDetailDto;
import com.dsfs.dsfs.dto.response.ReviewListDto;
import com.dsfs.dsfs.global.error.exception.GeneralException;
import com.dsfs.dsfs.global.error.status.ErrorStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    public CreatedReviewDto createReview(Long id, ReviewCreateDto req) {
        if(id == null) throw new GeneralException(ErrorStatus.INVALID_TOKEN_ERROR);

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

    public ReviewListDto getReviews(Long id, String query, Long restaurantId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviews;
        if(query.equals("my")){
            if(id == null) throw new GeneralException(ErrorStatus.INVALID_TOKEN_ERROR);
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
            reviews = reviewRepository.findAllByUser(user,pageRequest);
        } else if (query.equals("restaurant")) {
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
            reviews = reviewRepository.findAllByRestaurant(restaurant, pageRequest);
        } else {
            reviews = reviewRepository.findAll(pageRequest);
        }
        boolean isLast = reviews.isLast();
        int totalPage = reviews.getTotalPages();
        long totalElement = reviews.getTotalElements();
        List<ReviewDetailDto> reviewDetailDtos = reviews.stream().map(ReviewDetailDto::of).toList();

        return ReviewListDto.builder()
                .isLast(isLast)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .reviews(reviewDetailDtos)
                .build();
    }
}
