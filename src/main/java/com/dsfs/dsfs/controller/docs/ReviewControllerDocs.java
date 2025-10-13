package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.controller.ReviewQuery;
import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {
    @Operation(summary = "리뷰 작성")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 등록 성공")
    public ApiResponse<?> createReview(@LoginInfo Long id, @RequestBody ReviewCreateDto req);

    @Operation(summary = "리뷰 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 조회 성공")
    public ApiResponse<?> getReviews(
            @LoginInfo Long id,
            @RequestParam(value = "query", required = false) ReviewQuery query,
            @RequestParam(value = "r_id", required = false) Long restaurantId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size);
}

