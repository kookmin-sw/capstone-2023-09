package com.capstone.timepay.controller.board;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardRepository;
import com.capstone.timepay.service.board.dto.FreeBoardDTO;
import com.capstone.timepay.service.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{uuid}")
    @ApiOperation(value = "해당 유저의 게시판 조회")
    public ResponseEntity<List<Board>> getBoards(@PathVariable Long uuid) {
        List<Board> boards = boardService.getBoardsByUuid(uuid);
        if (boards.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }


    /**
     * 카테고리는 free와 deal가 있다
     */
    @GetMapping("/{uuid}/{category}")
    @ApiOperation(value = "해당 유저의 카테고리게시판 조회")
    public ResponseEntity<List<Board>> getUserBoardsByCategory(@PathVariable Long uuid,
                                                               @PathVariable String category) {
        List<Board> boards = boardService.getBoardsByUuidAndCategory(uuid, category);
        if (boards.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }
}
