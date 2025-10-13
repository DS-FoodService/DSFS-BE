package com.dsfs.dsfs.dto.response;

import com.dsfs.dsfs.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewListDto(
        @Schema(description = "리뷰 id")
        Long reviewId,
        @Schema(description = "별점")
        Double score,
        @Schema(description = "리뷰 내용")
        String content,
        @Schema(description = "작성자")
        String author,
        @Schema(description = "작성 일자")
        LocalDateTime createdAt
) {
    public static ReviewListDto of(Review review) {
        return ReviewListDto.builder()
                .reviewId(review.getReviewId())
                .score(review.getScore())
                .content(review.getContent())
                .author(review.getUser().getEmail())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
