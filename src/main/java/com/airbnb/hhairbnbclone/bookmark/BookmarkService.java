package com.airbnb.hhairbnbclone.bookmark;

import com.airbnb.hhairbnbclone.entity.Bookmark;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.repository.BookmarkRepository;
import com.airbnb.hhairbnbclone.room.DetailRoomService;
import com.airbnb.hhairbnbclone.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final DetailRoomService detailRoomService;


    // 북마크
    public String bookmark(Long roomId, User user) {
        // 숙소 있는지 확인
        detailRoomService.getRoom(roomId);

        // roomid와 user로 bookmark 이전에 했는지 확인
        Optional<Bookmark> bookmark = getBookmark(roomId, user);
        // 이전에 bookmark 안했으면 추가, 했으면 삭제
        if (bookmark.isEmpty()){
            saveBookmark(roomId, user);
            return "북마크 저장 성공";
        } else {
            deleteBookmark(bookmark.get().getId());
            return "북마크 삭제 성공";
        }
    }

    private Optional<Bookmark> getBookmark(Long roomId, User user) {
        return bookmarkRepository.findByRoomIdAndUser(roomId, user);
    }

    private void saveBookmark(Long roomId, User user) {
        bookmarkRepository.saveAndFlush(new Bookmark(roomId, user));
    }

    private void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }

}
