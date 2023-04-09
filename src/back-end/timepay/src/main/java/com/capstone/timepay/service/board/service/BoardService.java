package com.capstone.timepay.service.board.service;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardRepository;
import com.capstone.timepay.domain.board.BoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

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
