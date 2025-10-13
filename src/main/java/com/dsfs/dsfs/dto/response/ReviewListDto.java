package com.dsfs.dsfs.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewListDto(
        boolean isLast,
        int totalPage,
        long totalElement,
        List<ReviewDetailDto> reviews
) {
}
