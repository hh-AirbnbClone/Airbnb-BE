package com.airbnb.hhairbnbclone.reservation;

import com.airbnb.hhairbnbclone.entity.Comment;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Comment,Long> {
}
