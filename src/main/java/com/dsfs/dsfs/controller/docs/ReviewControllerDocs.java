package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
            @RequestParam(value = "query", required = false) @Parameter(description = "정렬순",
                    examples = {
                    @ExampleObject(name = "나의 리뷰 조회", summary = "나의 리뷰 조회", value = "my"),
                    @ExampleObject(name = "식당 별 리뷰 조회", description = "식당 Id를 꼭 넣어주세요.", summary = "식당 별 리뷰 조회", value = "restaurant"),
                    @ExampleObject(name = "전체 리뷰 조회", summary = "전체 리뷰 조회", value = "all")}) String query,
            @RequestParam(value = "r_id", required = false) Long restaurantId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size);
}

