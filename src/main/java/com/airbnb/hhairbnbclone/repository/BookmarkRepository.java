package com.airbnb.hhairbnbclone.repository;

import com.airbnb.hhairbnbclone.entity.Bookmark;
import com.airbnb.hhairbnbclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
//    Optional<Bookmark> findByRoomIdAndUser(Long roomId, User user);
    void deleteByRoomIdAndUser(Long roomId, User user);
    boolean existsByRoomIdAndUser(Long roomId, User user);
}
