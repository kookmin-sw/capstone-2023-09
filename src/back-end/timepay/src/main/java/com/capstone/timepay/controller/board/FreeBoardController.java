package com.capstone.timepay.controller.board;

import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.service.board.dto.FreeBoardDTO;
import com.capstone.timepay.service.board.service.FreeBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/free-boards")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("")
    @ApiOperation(value = "전체 자유게시판 조회")
    public ResponseEntity<Page<FreeBoardDTO>> getBoards(
            @RequestParam(value = "pagingIndex", defaultValue = "0") int pagingIndex,
            @RequestParam(value = "pagingSize", defaultValue = "50") int pagingSize)
    {
        Page<FreeBoardDTO> paging = freeBoardService.getBoards(pagingIndex, pagingSize);
        if (paging.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paging, HttpStatus.OK);
    }

    @ApiOperation(value = "개별 자유게시판 조회")
    @GetMapping("/{id}")
    public ResponseEntity<FreeBoardDTO> getBoard(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(freeBoardService.getBoard(id), HttpStatus.OK);
    }


    @ApiOperation(value = "자유게시글 작성")
    @PostMapping("/write")
    public ResponseEntity write(@RequestBody FreeBoardDTO freeBoardDTO)
    {
        return new ResponseEntity(freeBoardService.write(freeBoardDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "자유게시글 수정")
    @PutMapping("/update/{id}")
    public Map<String, Object> update(@RequestBody FreeBoardDTO freeBoardDTO, @PathVariable("id") Long id)
    {
        Map<String, Object> updateMap = new HashMap<>();
        FreeBoard freeBoard = freeBoardService.getId(id);
        if (freeBoard == null)
        {
            updateMap.put("success", false);
            updateMap.put("message", "해당 게시글을 찾을 수 없습니다.");
            return updateMap;
        }

        if (!freeBoard.getUuid().equals(freeBoardDTO.getUuid()))
        {
            updateMap.put("success", false);
            updateMap.put("message", "수정 권한이 없습니다");
            return updateMap;
        }

        freeBoardService.update(id, freeBoardDTO);
        updateMap.put("success", true);
        updateMap.put("freeBoard", freeBoard);
        return updateMap;
    }

    @ApiOperation(value = "자유게시글 삭제")
    @DeleteMapping ("/delete/{id}")
    public Map<String, Object> delete(@RequestBody FreeBoardDTO freeBoardDTO, @PathVariable("id") Long id)
    {
        Map<String, Object> deleteMap = new HashMap<>();
        FreeBoard freeBoard = freeBoardService.getId(id);
        if (freeBoard == null)
        {
            deleteMap.put("success", false);
            deleteMap.put("message", "해당 게시글을 찾을 수 없습니다.");
            return deleteMap;
        }

        if (!freeBoard.getUuid().equals(freeBoardDTO.getUuid()))
        {
            deleteMap.put("success", false);
            deleteMap.put("message", "수정 권한이 없습니다");
            return deleteMap;
        }

        freeBoardService.delete(id);
        deleteMap.put("success", true);
        return deleteMap;
    }

}