package com.capstone.timepay.service.board.dto;

import com.capstone.timepay.domain.board.Board;
import com.capstone.timepay.domain.board.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {
    private Long id;
    private Long uuid;
    private String title;
    private int timepay;
    private String category;
    private BoardStatus boardStatus;
    private boolean isHidden;

    public static BoardDTO toBoardDTO(Board board)
    {
        return new BoardDTO(
                board.getBoardId(),
                board.getUuid(),
                board.getTitle(),
                board.getTimePay(),
                board.getCategory(),
                board.getBoardStatus(),
                board.isHidden()
        );
    }
}
