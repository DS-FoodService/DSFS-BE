package com.dsfs.dsfs.controller.docs;

import com.dsfs.dsfs.dto.request.CreateBookmarkRequestDto;
import com.dsfs.dsfs.dto.request.CreateRestaurantRequestDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.global.auth.annotation.LoginInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookmarkControllerDocs {
    @Operation(summary = "즐겨찾기 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "즐겨찾기 등록 성공")
    public ApiResponse<?> createBookmark(@LoginInfo Long id, @RequestBody CreateBookmarkRequestDto req);

    @Operation(summary = "즐겨찾기한 식당 목록 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "나의 즐겨찾기 목록 조회 성공")
    public ApiResponse<?> getBookmarks(
            @LoginInfo Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size);

    @Operation(summary = "즐겨찾기 삭제")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "즐겨찾기 삭제 성공")
    public ApiResponse<?> deleteBookmark(@LoginInfo Long id, @PathVariable Long bookmarkId);
}
