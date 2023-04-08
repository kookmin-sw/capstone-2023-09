package com.capstone.timepay.service.board.service;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardRepository;
import com.capstone.timepay.domain.board.BoardStatus;
import com.capstone.timepay.domain.dealBoard.DealBoard;
import com.capstone.timepay.domain.dealBoard.DealBoardRepository;
import com.capstone.timepay.service.board.dto.DealBoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealBoardService
{
    private final DealBoardRepository dealBoardRepository;
    private final BoardRepository boardRepository;

    public DealBoard getId(Long id)
    {
        return dealBoardRepository.findById(id).orElse(null);
    }

    // 전체 게시물 조회
    @Transactional(readOnly = true)
    public Page<DealBoardDTO> getDealBoards(int pagingIndex, int paingSize)
    {
        Pageable pageable = PageRequest.of(pagingIndex, paingSize);
        Page<DealBoard> dealBoardPage = dealBoardRepository.findByIsHiddenFalse(pageable);
        List<DealBoardDTO> dealBoardDTOList = dealBoardPage.stream()
                .map(DealBoardDTO::toDealBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dealBoardDTOList, dealBoardPage.getPageable(), dealBoardPage.getTotalElements());
    }

    // 개별 게시물 조회
    @Transactional(readOnly = true)
    public DealBoardDTO getDealBoard(Long id)
    {
        DealBoard dealBoard = dealBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다");
        });
        DealBoardDTO dealBoardDTO = DealBoardDTO.toDealBoardDTO(dealBoard);
        return dealBoardDTO;
    }

    // 게시물 작성
    @Transactional
    public DealBoardDTO write(DealBoardDTO dealBoardDTO)
    {
        DealBoard dealBoard = new DealBoard();
        dealBoard.setTitle(dealBoardDTO.getTitle());
        dealBoard.setState(dealBoardDTO.getState());
        dealBoard.setContent(dealBoardDTO.getContent());
        dealBoard.setCategory(dealBoardDTO.getCategory());
        dealBoard.setLocation(dealBoardDTO.getLocation());
        dealBoard.setStartTime(dealBoardDTO.getEndTime());
        dealBoard.setPay(dealBoardDTO.getPay());
        dealBoard.setCreatedAt(LocalDateTime.now());
        dealBoard.setUpdatedAt(LocalDateTime.now());
        dealBoard.setHidden(dealBoardDTO.isHidden());
        dealBoard.setUuid(dealBoardDTO.getUuid());
        dealBoardRepository.save(dealBoard);

        /**
         Board를 통해 한꺼번에 조회할 수 있도록 추가
         */
        Board board = new Board();
        board.setBoardStatus(BoardStatus.MATCHING_IN_PROGRESS);
        board.setUuid(dealBoardDTO.getUuid());
        board.setTitle(dealBoardDTO.getTitle());
        board.setTimePay(dealBoardDTO.getPay());
        board.setCategory("deal");
        boardRepository.save(board);
        return DealBoardDTO.toDealBoardDTO(dealBoard);
    }

    // 게시물 수정
    @Transactional
    public DealBoardDTO update(Long id, DealBoardDTO boardDto) {
        DealBoard dealBoard = dealBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다");
        });

        dealBoard.setTitle(boardDto.getTitle());
        dealBoard.setContent(boardDto.getContent());
        dealBoard.setState(boardDto.getState());
        dealBoard.setCategory(boardDto.getCategory());
        dealBoard.setLocation(boardDto.getLocation());
        dealBoard.setStartTime(boardDto.getStartTime());
        dealBoard.setEndTime(boardDto.getEndTime());
        dealBoard.setPay(boardDto.getPay());
        dealBoard.setHidden(boardDto.isHidden());

        return DealBoardDTO.toDealBoardDTO(dealBoard);
    }

    @Transactional
    public void delete(Long id) {
        DealBoard dealBoard = dealBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });
        dealBoardRepository.deleteById(id);
    }
}
