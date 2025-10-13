package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.Restaurant;
import com.dsfs.dsfs.domain.Review;
import com.dsfs.dsfs.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByUser(User user, Pageable pageable);
    Page<Review> findAllByRestaurant(Restaurant restaurant, Pageable pageable);
}
