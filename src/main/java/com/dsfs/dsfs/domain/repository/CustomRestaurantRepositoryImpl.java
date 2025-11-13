package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.QMenu;
import com.dsfs.dsfs.domain.QRestaurant;
import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.enums.CampusType;
import com.dsfs.dsfs.domain.enums.Icon;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
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
    public Page<Restaurant> findRestaurantsByIconsAndCampusType(
            List<Icon> icons,
            CampusType campusType,
            Pageable pageable
    ) {
        QRestaurant restaurant = QRestaurant.restaurant;
        QMenu menu = QMenu.menu;

        BooleanBuilder builder = new BooleanBuilder();

        // 1) CampusType 필터 (null 이면 전체)
        if (campusType != null) {
            builder.and(restaurant.campusType.eq(campusType));
        }

        // 2) Icon 필터 (null 또는 빈 배열이면 전체)
        if (icons != null && !icons.isEmpty()) {
            builder.and(
                    JPAExpressions
                            .selectOne()
                            .from(menu)
                            .where(
                                    menu.restaurant.restaurantId.eq(restaurant.restaurantId)
                                            .and(menu.icons.any().in(icons))
                            )
                            .exists()
            );
        }

        // 실제 데이터 조회
        List<Restaurant> content = jpaQueryFactory
                .selectFrom(restaurant)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // total count
        Long total = jpaQueryFactory
                .select(restaurant.count())
                .from(restaurant)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

}
