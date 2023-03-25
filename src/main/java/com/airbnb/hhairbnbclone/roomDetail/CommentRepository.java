package com.airbnb.hhairbnbclone.roomDetail;

import com.airbnb.hhairbnbclone.entity.Comment;
import com.airbnb.hhairbnbclone.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByRoom(Room room);
}
