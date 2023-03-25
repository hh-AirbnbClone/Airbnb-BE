package com.airbnb.hhairbnbclone.mainRooms;

import com.airbnb.hhairbnbclone.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MainRoomsRepository extends JpaRepository<Room, Long> {
}
