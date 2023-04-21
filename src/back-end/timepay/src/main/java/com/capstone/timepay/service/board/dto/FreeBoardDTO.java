package com.capstone.timepay.service.board.dto;

import com.capstone.timepay.domain.freeBoard.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardDTO
{
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isHidden;

    public static FreeBoardDTO toFreeBoardDTO(FreeBoard freeBoard)
    {
        return new FreeBoardDTO(
                freeBoard.getF_boardId(),
                freeBoard.getTitle(),
                freeBoard.getContent(),
                freeBoard.getCategory(),
                freeBoard.getCreatedAt(),
                freeBoard.getUpdatedAt(),
                freeBoard.isHidden()
        );
    }
}
