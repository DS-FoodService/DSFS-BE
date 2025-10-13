package com.dsfs.dsfs.dto.response;

import lombok.Builder;

@Builder
public record CreatedBookmarkDto(
        Long bookmarkId
) {
}
