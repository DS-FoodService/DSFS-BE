package com.dsfs.dsfs.dto.response;

import com.dsfs.dsfs.domain.Menu;
import com.dsfs.dsfs.domain.enums.Icon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record MenuListDto(
        @Schema(description = "메뉴 id")
        Long menuId,
        @Schema(description = "메뉴 이름")
        String name,
        @Schema(description = "메뉴 가격")
        Double price,
        @Schema(description = "메뉴 이미지")
        String imgUrl,
        @Schema(description = "아이콘")
        List<Icon> icons
) {
    public static MenuListDto of(Menu menu) {
        return MenuListDto.builder()
                .menuId(menu.getMenuId())
                .name(menu.getName())
                .price(menu.getPrice())
                .imgUrl(menu.getImg_url())
                .icons(menu.getIcons())
                .build();
    }
}
