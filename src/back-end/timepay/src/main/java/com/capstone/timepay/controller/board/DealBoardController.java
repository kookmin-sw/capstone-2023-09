package com.capstone.timepay.controller.board;

import com.capstone.timepay.service.board.dto.DealBoardDTO;
import com.capstone.timepay.service.board.service.DealBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deal-boards")
public class DealBoardController
{
    private final DealBoardService dealBoardService;

    @ApiOperation(value = "거래게시판 모든 게시판 불러오기")
    @GetMapping("")
    public ResponseEntity<List<DealBoardDTO>> getBoards()
    {
        return new ResponseEntity(dealBoardService.getDealBoards(), HttpStatus.OK);
    }

    @ApiOperation(value = "거래게시판 개별 게시판 불러오기")
    @GetMapping("/{id}")
    public ResponseEntity<DealBoardDTO> getBoard(@PathVariable("id") Long id)
    {
        return new ResponseEntity(dealBoardService.getDealBoard(id), HttpStatus.OK);
    }

    // 숨김처리 안된 게시물 조회
//    @ResponseEntityStatus(HttpStatus.OK)
//    @GetMapping("/good")
//    public ResponseEntity<?> getGoodBoards()
//    {
//        return new ResponseEntity("SUCCESS", "숨김처리 안된 게시판 조회", dealBoardService.getGoodBoard());
//    }
//
//
//    // 숨김처리된 게시물 조회
//    @ResponseEntityStatus(HttpStatus.OK)
//    @GetMapping("/bad")
//    public ResponseEntity<?> getBadBoards()
//    {
//        return new ResponseEntity("SUCCESS", "숨김처리 게시판 조회", dealBoardService.getBadBoard());
//    }
//

    @ApiOperation(value = "거래게시판 게시글 작성")
    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody DealBoardDTO dealBoardDTO)
    {
        return new ResponseEntity(dealBoardService.write(dealBoardDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "거래게시판 게시글 수정")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody DealBoardDTO dealBoardDTO, @PathVariable("id") Long id)
    {
        return new ResponseEntity(dealBoardService.update(id, dealBoardDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "거래게시판 게시글 삭제")
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        dealBoardService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
