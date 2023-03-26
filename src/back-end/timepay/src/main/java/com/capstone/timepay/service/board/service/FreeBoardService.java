package com.capstone.timepay.service.board.service;

import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.domain.freeBoard.FreeBoardRepository;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardComment;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardCommentRepository;
import com.capstone.timepay.domain.user.User;
import com.capstone.timepay.service.board.dto.FreeBoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FreeBoardService
{
    private final FreeBoardRepository freeBoardRepository;
    private final FreeBoardCommentRepository freeBoardCommentRepository;

    public FreeBoard getId(Long id)
    {
        return freeBoardRepository.findById(id).orElse(null);
    }

    // 모든 게시물 조회 (숨김처리 안된 게시판만 조회, hidden이라는 쿼리로 판단)
    @Transactional
    public Page<FreeBoardDTO> getBoards(int pagingIndex, int pagingSize)
    {
        Pageable pageable = PageRequest.of(pagingIndex, pagingSize);
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findByIsHiddenFalse(pageable);
        List<FreeBoardDTO> freeBoardDTOList = freeBoardPage.stream()
                .map(FreeBoardDTO::toFreeBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(freeBoardDTOList, freeBoardPage.getPageable(), freeBoardPage.getTotalElements());
    }

    // 개별 게시물 조회
    @Transactional(readOnly = true)
    public FreeBoardDTO getBoard(Long id)
    {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다.");
        });
        FreeBoardDTO freeBoardDTO = FreeBoardDTO.toFreeBoardDTO(freeBoard);
        return freeBoardDTO;
    }

    // 게시물 작성
    @Transactional
    public FreeBoardDTO write(FreeBoardDTO freeBoardDTO)
    {
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setTitle(freeBoardDTO.getTitle());
        freeBoard.setContent(freeBoardDTO.getContent());
        freeBoard.setCategory(freeBoardDTO.getCategory());
        freeBoard.setCreatedAt(LocalDateTime.now());
        freeBoard.setUpdatedAt(LocalDateTime.now());
        freeBoard.setHidden(freeBoardDTO.isHidden());
        // TODO: uuid 정보는 숨겨야하지 않을까
        freeBoard.setUuid(freeBoardDTO.getUuid());
        freeBoardRepository.save(freeBoard);
        return FreeBoardDTO.toFreeBoardDTO(freeBoard);
    }

    // 게시물 수정
    @Transactional
    public FreeBoardDTO update(Long id, FreeBoardDTO freeBoardDTO)
    {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다.");
        });
        freeBoard.setTitle(freeBoardDTO.getTitle());
        freeBoard.setContent(freeBoardDTO.getContent());
        freeBoard.setCategory(freeBoardDTO.getCategory());
        freeBoard.setUpdatedAt(LocalDateTime.now());
        freeBoard.setHidden(freeBoardDTO.isHidden());
        return FreeBoardDTO.toFreeBoardDTO(freeBoard);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long id, FreeBoardDTO freeBoardDTO)
    {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });
        freeBoardRepository.deleteById(id);
    }
}
