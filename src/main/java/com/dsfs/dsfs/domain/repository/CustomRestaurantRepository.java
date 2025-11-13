package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.enums.CampusType;
import com.dsfs.dsfs.domain.enums.Icon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomRestaurantRepository {
    Page<Restaurant> findRestaurantsByIconsAndCampusType(List<Icon> icons, CampusType campusType, Pageable pageable);
}
