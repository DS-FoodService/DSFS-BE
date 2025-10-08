package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.enums.Icon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByIcons(List<Icon> icons, PageRequest pageRequest);
}
