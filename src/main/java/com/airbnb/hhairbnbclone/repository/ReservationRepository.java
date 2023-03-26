package com.airbnb.hhairbnbclone.repository;

import com.airbnb.hhairbnbclone.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
//    Optional<Reservation> findById(Long roomId);
//
//    List<Reservation> findAllByCheckInBetweenOrCheckOutBetween(Date checkIn, Date checkOut);
}
