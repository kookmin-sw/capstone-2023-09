package com.capstone.timepay.service.board.service;

import com.capstone.timepay.domain.comment.Comment;
import com.capstone.timepay.domain.comment.CommentRepository;
import com.capstone.timepay.domain.dealBoard.DealBoard;
import com.capstone.timepay.domain.dealBoard.DealBoardRepository;
import com.capstone.timepay.domain.dealBoardComment.DealBoardComment;
import com.capstone.timepay.domain.dealBoardComment.DealBoardCommentRepository;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardComment;
import com.capstone.timepay.service.board.dto.DealBoardCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DealBoardCommentService {

    private final DealBoardCommentRepository dealBoardCommentRepository;
    private final DealBoardRepository dealBoardRepository;
    private final CommentRepository commentRepository;

    // 댓글 작성하기
    @Transactional
    public DealBoardCommentDTO writeComment(Long boardId, DealBoardCommentDTO dealBoardCommentDTO)
    {
        DealBoardComment dealBoardComment = new DealBoardComment();
        dealBoardComment.setContent(dealBoardCommentDTO.getContent());

        DealBoard dealBoard = dealBoardRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없음");
        });

        dealBoardComment.setUuid(dealBoardCommentDTO.getUuid());
        dealBoardComment.setDealBoard(dealBoard);
        dealBoardComment.setApplied(false);
        dealBoardComment.setAdopted(false);
        // TODO: 유저에 따라 숨김처리 하냐 안하냐 결정

        dealBoardCommentRepository.save(dealBoardComment);

        /**
         * 댓글 전체 조회를 위한 로직
         */
        Comment comment = new Comment();
        comment.setUuid(dealBoardCommentDTO.getUuid());
        comment.setContent(dealBoardCommentDTO.getContent());
        comment.setBoardTitle(dealBoard.getTitle());
        commentRepository.save(comment);

        return DealBoardCommentDTO.toDealBoardCommentDTO(dealBoardComment);
    }

    // 전체 댓글 불러오기
    // TODO: 비공개 유저일때 제거해야함
    @Transactional(readOnly = true)
    public List<DealBoardCommentDTO> getComments(Long boardId)
    {
        List<DealBoardComment> comments = dealBoardCommentRepository.findAllByDealBoard(dealBoardRepository.findById(boardId).get());
        List<DealBoardCommentDTO> commentDTOS = new ArrayList<>();

        comments.forEach(s -> commentDTOS.add(DealBoardCommentDTO.toDealBoardCommentDTO(s)));
        return commentDTOS;
    }

    // 삭제
    @Transactional(readOnly = true)
    public void delete(Long commentId) {
        DealBoardComment dealBoardComment = dealBoardCommentRepository.findById(commentId).orElseThrow(() -> {
            return new IllegalArgumentException("Comment Id를 찾을 수 없습니다");
        });
        dealBoardCommentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    public DealBoardComment getCommentId(Long id)
    {
        return dealBoardCommentRepository.findById(id).orElse(null);
    }




}
