package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.bookmark.BookmarkService;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.reservation.ReservationService;
import com.airbnb.hhairbnbclone.room.dto.MainRoomsResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomRequestDto;
import com.airbnb.hhairbnbclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final ReservationService reservationService;
    private final BookmarkService bookmarkService;

    @Transactional
    public void createRoom(RoomRequestDto requestDto) {
        roomRepository.saveAndFlush(new Room(requestDto));
    }

    @Transactional(readOnly = true)
    public List<MainRoomsResponseDto> getMainRooms(String address, LocalDate checkInDate, LocalDate checkOutDate, Integer guestNum, UserDetailsImpl userDetails) {
        User user = userDetails == null ? null : userDetails.getUser();
        List<MainRoomsResponseDto> mainRoomsResponseDtoList = new ArrayList<>();
        for (Room room : roomRepository.findAll()) {
            if (address == null || checkInDate == null || checkOutDate == null || guestNum == null) {
                Map<String, LocalDate> getOneWeek = reservationService.getEarliestAvailableDate(room);
                checkInDate = getOneWeek.get("startDate");
                checkOutDate = getOneWeek.get("endDate");
            } else {
                List<Long> notReservableRoomList = reservationService.getNotReservableRoom(checkInDate, checkOutDate);
                if (!room.getAddress().contains(address) || notReservableRoomList.contains(room.getId()) || guestNum > room.getMaxGuest()) {
                    continue;
                }
            }
            boolean isBookmarked = bookmarkService.checkBookmark(room.getId(), user);
            mainRoomsResponseDtoList.add(new MainRoomsResponseDto(room, checkInDate, checkOutDate, isBookmarked));
        }

        return mainRoomsResponseDtoList;
    }
}
