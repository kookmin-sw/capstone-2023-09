package com.capstone.timepay.service.board.dto;

import com.capstone.timepay.domain.freeBoard.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static FreeBoardDTO toFreeBoardDTO(FreeBoard freeBoard)
    {
        return new FreeBoardDTO(
                freeBoard.getId(),
                freeBoard.getTitle(),
                freeBoard.getContent(),
                freeBoard.getCategory()
                // TODO : 나중에 유저도 넣어줘야함
        );
    }
}
