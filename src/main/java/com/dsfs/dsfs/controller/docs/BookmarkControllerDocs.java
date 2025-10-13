package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.dto.request.CreateBookmarkRequestDto;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookmarkControllerDocs {
    @Operation(summary = "즐겨찾기 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식당 등록 성공")
    public ApiResponse<?> createBookmark(@LoginInfo Long id, @RequestBody CreateBookmarkRequestDto req);
}
