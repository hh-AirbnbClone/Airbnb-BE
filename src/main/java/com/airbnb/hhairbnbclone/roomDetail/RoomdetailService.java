package com.airbnb.hhairbnbclone.roomDetail;

import com.airbnb.hhairbnbclone.entity.Comment;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.reservation.ReservationRepository;
import com.airbnb.hhairbnbclone.roomDetail.dto.CommentRequestDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.CommentResponseDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.RoomDetailListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class RoomdetailService {
    private final RoomDetailRepository roomDetailRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public RoomDetailListResponseDto getDetailRoom(Long roomId){
        Room room = roomDetailRepository.findById(roomId).orElseThrow(    // 원하는 숙소 찾았음
                () -> new CustomException(CustomErrorCode.NOT_PROPER_INPUTFORM)
        );
        List<Comment> commentList = commentRepository.findByRoom(room);
        if(commentList.size()==0){
            new CustomException(CustomErrorCode.NOT_PROPER_INPUTFORM);
        }
        return new RoomDetailListResponseDto(room, commentList);
    }


//    public CommentResponseDto createComment(Long roomId, CommentRequestDto commentRequestDto, User user){
//        Room room = roomDetailRepository.findById(roomId).orElseThrow(    // 원하는 숙소 찾았음
//                () -> new CustomException(CustomErrorCode.NOT_PROPER_INPUTFORM)
//        );
//
//        Comment comment = new Comment(commentRequestDto, user); //username이랑 comment 있음
//        commentRepository.saveAndFlush(comment);
//        return new ReplyResponseDto(comment);
//    }
}
