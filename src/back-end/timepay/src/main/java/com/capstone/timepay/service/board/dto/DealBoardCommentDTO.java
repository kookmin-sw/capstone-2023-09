package com.capstone.timepay.service.board.dto;

import com.capstone.timepay.domain.dealBoardComment.DealBoardComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealBoardCommentDTO {
    private Long id;
    private String content;
    private Long uuid;
    private boolean isApplied;
    private boolean isAdopted;
    private boolean isHidden;

    public static DealBoardCommentDTO toDealBoardCommentDTO(DealBoardComment dealBoardComment)
    {
        return new DealBoardCommentDTO(
                dealBoardComment.getD_commentId(),
                dealBoardComment.getContent(),
                dealBoardComment.getUuid(),
                dealBoardComment.isApplied(),
                dealBoardComment.isAdopted(),
                dealBoardComment.isHidden()
        );
    }
}
