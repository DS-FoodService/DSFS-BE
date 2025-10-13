package com.dsfs.dsfs.controller;

import com.dsfs.dsfs.controller.docs.ReviewControllerDocs;
import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.service.ReviewService;
import lombok.RequiredArgsConstructor;
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
}
