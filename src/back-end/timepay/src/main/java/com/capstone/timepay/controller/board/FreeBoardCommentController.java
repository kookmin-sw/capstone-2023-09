package com.capstone.timepay.controller.board;

import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.domain.freeBoard.FreeBoardRepository;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardComment;
import com.capstone.timepay.service.board.dto.FreeBoardCommentDTO;
import com.capstone.timepay.service.board.dto.FreeBoardDTO;
import com.capstone.timepay.service.board.service.FreeBoardCommentService;
import com.capstone.timepay.service.board.service.FreeBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        return new ResponseEntity(freeBoardCommentService.writeComment(boardId, freeBoardCommentDTO, freeBoardCommentDTO.getUuid()), HttpStatus.OK);
    }

    @ApiOperation(value = "자유게시글 게시판의 댓글 불러오기")
    @GetMapping("/{boardId}")
    public ResponseEntity getFreeBoardComments(@PathVariable("boardId") Long boardId)
    {
        return new ResponseEntity(freeBoardCommentService.getComments(boardId), HttpStatus.OK);
    }

    @ApiOperation("자유게시판 댓글 삭")
    @DeleteMapping("/{boardId}/{commentId}")
    public Map<String, Object> deleteFreeBoardComment(@RequestBody FreeBoardCommentDTO freeBoardCommentDTO, @PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
        Map<String, Object> deleteMap = new HashMap<>();
        FreeBoardComment freeBoardComment = freeBoardCommentService.getCommentId(commentId);

        if (freeBoardComment == null)
        {
            deleteMap.put("success", false);
            deleteMap.put("message", "해당 댓글을 찾을 수가 없습니다");
        }

        if (!freeBoardComment.getUuid().equals(freeBoardCommentDTO.getUuid()))
        {
            deleteMap.put("success", false);
            deleteMap.put("message", "삭제 권한이 없습니다");
        }
        freeBoardCommentService.delete(commentId);
        deleteMap.put("success", false);
        deleteMap.put("message", freeBoardComment);
        return deleteMap;
    }
}
