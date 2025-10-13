package com.dsfs.dsfs.controller;


import com.dsfs.dsfs.controller.docs.BookmarkControllerDocs;
import com.dsfs.dsfs.dto.request.CreateBookmarkRequestDto;
import com.dsfs.dsfs.global.ApiResponse;
import com.dsfs.dsfs.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkController implements BookmarkControllerDocs {
    final private BookmarkService bookmarkService;
    @PostMapping
    public ApiResponse<?> createBookmark(Long id, CreateBookmarkRequestDto req) {
        return ApiResponse.onSuccess(bookmarkService.createBookmark(id, req));
    }
}
