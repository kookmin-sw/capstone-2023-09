package com.capstone.timepay.controller.board;

import com.capstone.timepay.controller.board.annotation.Response;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.service.board.dto.FreeBoardDTO;
import com.capstone.timepay.service.board.service.FreeBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/free-boards")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("")
    @ApiOperation(value = "전체 자유게시판 조회")
    public ResponseEntity<List<FreeBoardDTO>> getBoards()
    {
        List<FreeBoardDTO> freeBoardDTOList = freeBoardService.getBoards();
        if (freeBoardDTOList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(freeBoardDTOList, HttpStatus.OK);
    }

    @ApiOperation(value = "개별 자유게시판 조회")
    @GetMapping("/{id}")
    public ResponseEntity<FreeBoardDTO> getBoard(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(freeBoardService.getBoard(id), HttpStatus.OK);
    }

//    // 숨김처리 안된 게시물 조회
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/good")
//    public Response<?> getGoodBoards()
//    {
//        return new Response("SUCCESS", "숨김처리 안된 게시판 조회", freeBoardService.getGoodBoard());
//    }
//
//
//    // 숨김처리된 게시물 조회
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/bad")
//    public Response<?> getBadBoards()
//    {
//        return new Response("SUCCESS", "숨김처리 게시판 조회", freeBoardService.getBadBoard());
//    }


    @ApiOperation(value = "자유게시글 작성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/write")
    public ResponseEntity write(@RequestBody FreeBoardDTO freeBoardDTO)
    {
        return new ResponseEntity(freeBoardService.write(freeBoardDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "자유게시글 수정")
    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody FreeBoardDTO freeBoardDTO, @PathVariable("id") Long id)
    {
        return new ResponseEntity(freeBoardService.update(id, freeBoardDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "자유게시글 삭제")
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        freeBoardService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
