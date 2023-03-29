package com.airbnb.hhairbnbclone.repository;

import com.airbnb.hhairbnbclone.entity.Reservation;
import com.airbnb.hhairbnbclone.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByRoom(Room room);
}
