package com.dsfs.dsfs.domain.repository;

import com.dsfs.dsfs.domain.Bookmark;
import com.dsfs.dsfs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByUser(User user);
}
