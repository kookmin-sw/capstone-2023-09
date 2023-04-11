package com.capstone.timepay.service.board.service;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardRepository;
import com.capstone.timepay.domain.board.BoardSearch;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.service.board.dto.BoardDTO;
import com.capstone.timepay.service.board.dto.FreeBoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 모든 게시물 조회 (숨김처리 안된 게시판만 조회, hidden이라는 쿼리로 판단)
    @Transactional
    public Page<BoardDTO> getBoards(int pagingIndex, int pagingSize)
    {
        Pageable pageable = PageRequest.of(pagingIndex, pagingSize);
        Page<Board> boardPage = boardRepository.findByIsHiddenFalse(pageable);
        List<BoardDTO> boardDTOList = boardPage.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(boardDTOList, boardPage.getPageable(), boardPage.getTotalElements());
    }

    public List<Board> getBoardsByUuid(Long uuid) {
        return boardRepository.findByUuid(uuid);
    }

    public List<Board> getBoardsByUuidAndCategory(Long uuid, String category)
    {
        return boardRepository.getBoardsByUuidAndCategory(uuid, category);
    }

    public List<Board> boardSearch(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String category)
    {
        Specification<Board> spec = Specification.where(null);

        if (sort != null) {
            if (sort.equals("latest")) {
                spec = spec.and(BoardSearch.latest());
            } else if (sort.equals("oldest")) {
                spec = spec.and(BoardSearch.oldest());
            }
        }

        if (category != null) {
            if (category.equals("free")) {
                spec = spec.and(BoardSearch.withCategory("freeBoard"));
            } else if (category.equals("deal")) {
                spec = spec.and(BoardSearch.withCategory("dealBoard"));
            }
        }
        return boardRepository.findAll(spec);
    }
}
