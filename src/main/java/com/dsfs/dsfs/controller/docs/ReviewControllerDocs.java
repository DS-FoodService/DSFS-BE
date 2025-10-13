package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.domain.enums.Icon;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.dto.request.ReviewCreateDto;
import com.dsfs.dsfs.dto.response.RestaurantListDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewControllerDocs {@Operation(summary = "리뷰 작성")
@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 등록 성공")
public ApiResponse<?> createReview(@LoginInfo Long id, @RequestBody ReviewCreateDto req);
}
