package com.airbnb.hhairbnbclone.reservation;
import com.airbnb.hhairbnbclone.entity.Comment;
import com.airbnb.hhairbnbclone.entity.Reservation;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findById(Long roomId);

    List<Reservation> findAllByCheckInBetweenOrCheckOutBetween(Date checkIn, Date checkOut);
}
