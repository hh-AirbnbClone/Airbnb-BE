package com.airbnb.hhairbnbclone.roomDetail;

import com.airbnb.hhairbnbclone.entity.Room;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomDetailRepository extends JpaRepository<Room,Long> {

}
