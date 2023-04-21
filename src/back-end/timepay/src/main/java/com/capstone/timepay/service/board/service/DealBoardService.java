package com.capstone.timepay.service.board.service;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardRepository;
import com.capstone.timepay.domain.board.BoardStatus;
import com.capstone.timepay.domain.dealBoard.DealBoard;
import com.capstone.timepay.domain.dealBoard.DealBoardRepository;
import com.capstone.timepay.domain.dealRegister.DealRegister;
import com.capstone.timepay.domain.dealRegister.DealRegisterRepository;
import com.capstone.timepay.domain.user.User;
import com.capstone.timepay.domain.user.UserRepository;
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
    private final DealRegisterRepository dealRegisterRepository;
    private final UserRepository userRepository;

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

    // 도움주기 게시물 조회
    @Transactional(readOnly = true)
    public Page<DealBoardDTO> getHelperDealBoard(int pagingIndex, int paingSize)
    {
        Pageable pageable = PageRequest.of(pagingIndex, paingSize);
        Page<DealBoard> dealBoardPage = dealBoardRepository.findByCategory(pageable, "helper");
        List<DealBoardDTO> dealBoardDTOList = dealBoardPage.stream()
                .map(DealBoardDTO::toDealBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dealBoardDTOList, dealBoardPage.getPageable(), dealBoardPage.getTotalElements());
    }

    // 도움받기 게시물 조회
    @Transactional(readOnly = true)
    public Page<DealBoardDTO> getHelpDealBoard(int pagingIndex, int paingSize)
    {
        Pageable pageable = PageRequest.of(pagingIndex, paingSize);
        Page<DealBoard> dealBoardPage = dealBoardRepository.findByCategory(pageable, "help");
        List<DealBoardDTO> dealBoardDTOList = dealBoardPage.stream()
                .map(DealBoardDTO::toDealBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dealBoardDTOList, dealBoardPage.getPageable(), dealBoardPage.getTotalElements());
    }

    // 게시물 작성
    @Transactional
    public DealBoardDTO write(DealBoardDTO dealBoardDTO, String email, String category)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            return new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
        });

        DealBoard dealBoard = DealBoard.builder()
                .title(dealBoardDTO.getTitle())
                .state(dealBoardDTO.getState())
                .content(dealBoardDTO.getContent())
                .category(category)
                .location(dealBoardDTO.getLocation())
                .startTime(dealBoardDTO.getEndTime())
                .pay(dealBoardDTO.getPay())
                .isHidden(dealBoardDTO.isHidden())
                .build();

        Board board = Board.builder().
                freeBoard(null).
                dealBoard(dealBoard).
                build();
        boardRepository.save(board);

        DealRegister dealRegister = DealRegister.builder()
                .d_registerId(dealBoard.getD_boardId())
                .dealBoard(dealBoard)
                .user(user)
               .build();
               dealRegisterRepository.save(dealRegister);

        dealBoardRepository.save(dealBoard);
        return DealBoardDTO.toDealBoardDTO(dealBoard);
    }

    // 게시물 수정
    @Transactional
    public DealBoardDTO update(Long id, DealBoardDTO boardDto) {
        DealBoard dealBoard = dealBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다");
        });

        dealBoard = DealBoard.builder()
                .title(boardDto.getTitle())
                .state(boardDto.getState())
                .content(boardDto.getContent())
                .category(boardDto.getCategory())
                .location(boardDto.getLocation())
                .startTime(boardDto.getEndTime())
                .pay(boardDto.getPay())
                .isHidden(boardDto.isHidden())
                .build();

        return DealBoardDTO.toDealBoardDTO(dealBoard);
    }

    @Transactional
    public void delete(Long id) {
        // 매개변수 id를 기반으로, 게시글이 존재하는지 먼저 찾음
        // 게시글이 없으면 오류 처리
        DealBoard dealBoard = dealBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });

        // 게시글이 있는 경우 삭제처리
        dealBoardRepository.deleteById(id);
    }

    @Transactional
    public DealBoardDTO modifyStatus(Long boardId, DealBoardDTO boardDto)
    {
        DealBoard dealBoard = dealBoardRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다");
        });

        dealBoard = DealBoard.builder()
                .boardStatus(BoardStatus.MATCHING_COMPLETE)
                .build();
        return DealBoardDTO.toDealBoardDTO(dealBoard);
    }
}
