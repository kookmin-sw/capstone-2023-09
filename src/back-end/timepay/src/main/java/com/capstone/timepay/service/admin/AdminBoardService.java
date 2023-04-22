package com.capstone.timepay.service.admin;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardRepository;
import com.capstone.timepay.domain.board.BoardSearch;
import com.capstone.timepay.domain.dealBoard.DealBoard;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.service.admin.dto.AdminBoardDTO;
import com.capstone.timepay.service.admin.dto.AdminDealBoardDTO;
import com.capstone.timepay.service.admin.dto.AdminFreeBoardDTO;
import com.capstone.timepay.service.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminBoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Page<AdminBoardDTO> getAllBoards(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Board> boards = boardRepository.findAll(pageable);
        List<AdminBoardDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            AdminBoardDTO boardDTO = new AdminBoardDTO();

            FreeBoard freeBoard = board.getFreeBoard();
            if (freeBoard != null) {
                AdminFreeBoardDTO freeBoardDTO = AdminFreeBoardDTO.toFreeBoardDTO(freeBoard);
                boardDTO.setFreeBoardDTO(freeBoardDTO);
            }

            DealBoard dealBoard = board.getDealBoard();
            if (dealBoard != null) {
                AdminDealBoardDTO dealBoardDTO = AdminDealBoardDTO.toDealBoardDTO(dealBoard);
                boardDTO.setDealBoardDTO(dealBoardDTO);
            }

            boardDTOs.add(boardDTO);
        }

        return new PageImpl<>(boardDTOs, pageable, boards.getTotalElements());
    }

    public enum SortType {
        LATEST_FIRST, OLDEST_FIRST
    }

    public List<Board> search(BoardService.SortType sortType, boolean freeBoard, boolean dealBoard, String user, String category) {
        Specification<Board> spec = Specification.where(null);

        switch (sortType) {
            case LATEST_FIRST:
                spec = spec.and(BoardSearch.latestFirst());
                break;
            case OLDEST_FIRST:
                spec = spec.and(BoardSearch.oldestFirst());
                break;
            default:
                break;
        }

        if (freeBoard) {
            spec = spec.and(BoardSearch.freeBoard());
        }

        if (dealBoard) {
            spec = spec.and(BoardSearch.dealBoard());
        }

        if (user != null) {
            spec = spec.and(BoardSearch.createdBy(user));
        }

        if (category != null) {
            spec = spec.and(BoardSearch.category(category));
        }

        return boardRepository.findAll(spec);
    }
}