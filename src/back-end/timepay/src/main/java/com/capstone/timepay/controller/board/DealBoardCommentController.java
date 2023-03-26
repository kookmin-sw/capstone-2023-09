package com.capstone.timepay.controller.board;

import com.capstone.timepay.service.board.dto.DealBoardCommentDTO;
import com.capstone.timepay.service.board.service.DealBoardCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deal-boards")
public class DealBoardCommentController {

    private final DealBoardCommentService dealBoardCommentService;

    @ApiOperation(value = "거래게시글 댓글 삭제")
    @PostMapping("/comments/{boardId}")
    public ResponseEntity writeDealBoardComment(@PathVariable("boardId") Long boardId, @RequestBody DealBoardCommentDTO dealBoardCommentDTO)
    {
        return new ResponseEntity(dealBoardCommentService.writeComment(boardId, dealBoardCommentDTO, dealBoardCommentDTO.getUid()), HttpStatus.OK);
    }

    @ApiOperation(value = "거래게시글 게시판의 모든 댓글 불러오기")
    // 게시글에 달린 댓글 모두 불러오기
    @GetMapping("/comments/{boardId}")
    public ResponseEntity getDealBoardComments(@PathVariable("boardId") Long boardId) {
        return new ResponseEntity(dealBoardCommentService.getComments(boardId), HttpStatus.OK);
    }

    // 댓글 삭제
    @ApiOperation(value = "거래게시글 댓글 삭제")
    @DeleteMapping("/comments/{boardId}/{commentId}")
    public ResponseEntity deleteDealBoardComment(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
        // TODO: 추후 JWT 로그인 기능을 추가하고나서, 세션에 로그인된 유저와 댓글 작성자를 비교해서, 맞으면 삭제 진행하고
        // 틀리다면 예외처리를 해주면 된다.
        dealBoardCommentService.deleteComment(commentId);
        return new ResponseEntity( HttpStatus.OK);
    }
}
