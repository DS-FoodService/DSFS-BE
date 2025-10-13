package com.dsfs.dsfs.controller;

import io.swagger.v3.oas.annotations.media.Schema;

public enum ReviewQuery {
    @Schema(description = "현재 로그인한 사용자가 작성한 리뷰 조회")
    MY,
    @Schema(description = "특정 식당의 리뷰 조회")
    RESTAURANT
}
