package com.airbnb.hhairbnbclone.repository;

import com.airbnb.hhairbnbclone.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    List<Room> findAllByIdNotIn(List<Long> roomList);
}

