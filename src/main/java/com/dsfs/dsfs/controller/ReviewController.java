package com.dsfs.dsfs.controller;

import com.dsfs.dsfs.controller.docs.ReviewControllerDocs;
import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.dto.response.ReviewListDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {
    private final ReviewService reviewService;
    @PostMapping
    public ApiResponse<?> createReview(Long id, ReviewCreateDto req) {
        return ApiResponse.onSuccess(reviewService.createReview(id, req));
    }

    @GetMapping
    public ApiResponse<ReviewListDto> getReviews(Long id, ReviewQuery query, Long restaurantId, int page, int size) {
        return ApiResponse.onSuccess(reviewService.getReviews(id, query, restaurantId, page, size));
    }
}
