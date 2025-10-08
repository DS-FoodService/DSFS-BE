package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.QMenu;
import com.dsfs.dsfs.domain.QRestaurant;
import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.enums.Icon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRestaurantRepositoryImpl implements CustomRestaurantRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QRestaurant r = QRestaurant.restaurant;
    private static final QMenu m = QMenu.menu;

    @Override
    public Page<Restaurant> findRestaurantsByIcons(List<Icon> icons, Pageable pageable) {
        // 아이콘 필터 비어있으면 전체 조회
        if (icons == null || icons.isEmpty()) {
            List<Restaurant> content = jpaQueryFactory
                    .selectFrom(r)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            Long total = jpaQueryFactory
                    .select(r.count())
                    .from(r)
                    .fetchOne();

            return new PageImpl<>(content, pageable, total == null ? 0 : total);
        }

        // OR 조건: 선택한 아이콘 중 하나라도 포함
        List<Restaurant> content = jpaQueryFactory
                .selectDistinct(r)
                .from(r)
                .join(r.menus, m)
                .where(m.icons.any().in(icons))   // 핵심
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(r.restaurantId.countDistinct())
                .from(r)
                .join(r.menus, m)
                .where(m.icons.any().in(icons))   // 동일 조건
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
