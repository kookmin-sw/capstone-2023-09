package com.capstone.timepay.controller.board;

import com.capstone.timepay.service.board.dto.FreeBoardCommentDTO;
import com.capstone.timepay.service.board.service.FreeBoardCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/free-boards/comments")
public class FreeBoardCommentController {

    private final FreeBoardCommentService freeBoardCommentService;

    // 댓글 작성
    @ApiOperation(value = "자유게시글 댓글작성")
    @PostMapping("/{boardId}")
    public ResponseEntity writeFreeBoardComment(@PathVariable("boardId") Long boardId, @RequestBody FreeBoardCommentDTO freeBoardCommentDTO)
    {
        // TODO: 추후 User 정보는 세션을 통해 주고받도록 수정
        return new ResponseEntity(freeBoardCommentService.writeComment(boardId, freeBoardCommentDTO, freeBoardCommentDTO.getUid()), HttpStatus.OK);
    }

    @ApiOperation(value = "자유게시글 게시판의 댓글 불러오기")
    @GetMapping("/{boardId}")
    public ResponseEntity getFreeBoardComments(@PathVariable("boardId") Long boardId)
    {
        return new ResponseEntity(freeBoardCommentService.getComments(boardId), HttpStatus.OK);
    }

    @ApiOperation(value = "자유게시글 게시판의 특정 댓글 삭제")
    @GetMapping("/{boardId}/{commentId}")
    public ResponseEntity deleteFreeBoardComment(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId)
    {
        // TODO: 후 JWT 로그인 기능을 추가하고나서, 세션에 로그인된 유저와 댓글 작성자를 비교해서,
        //  맞으면 삭
        return new ResponseEntity(freeBoardCommentService.deleteComment(commentId), HttpStatus.OK);
    }
}
