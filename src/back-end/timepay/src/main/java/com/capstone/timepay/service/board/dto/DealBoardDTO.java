package com.capstone.timepay.service.board.dto;

import com.capstone.timepay.domain.board.BoardStatus;
import com.capstone.timepay.domain.dealAttatchment.DealAttatchment;
import com.capstone.timepay.domain.dealBoard.DealBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealBoardDTO
{
    private Long dealBoardId;
    private String title;
    private BoardStatus state;
    private String type;
    private String content;
    private String category;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int pay;
    private boolean isHidden;
    private boolean isAuto;
    private List<DealAttatchment> images;

    public static DealBoardDTO toDealBoardDTO(DealBoard dealBoard)
    {
        return new DealBoardDTO(
                dealBoard.getD_boardId(),
                dealBoard.getTitle(),
                dealBoard.getBoardStatus(),
                dealBoard.getType(),
                dealBoard.getContent(),
                dealBoard.getCategory(),
                dealBoard.getLocation(),
                dealBoard.getStartTime(),
                dealBoard.getEndTime(),
                dealBoard.getPay(),
                dealBoard.isHidden(),
                dealBoard.isAuto(),
                dealBoard.getDealAttatchments()
        );
    }
}
