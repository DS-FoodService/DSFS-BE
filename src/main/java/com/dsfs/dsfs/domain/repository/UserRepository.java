package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
    User findByEmail(String email);
}
