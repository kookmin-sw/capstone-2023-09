package com.capstone.timepay.domain.comment;

import com.capstone.timepay.domain.BaseTimeEntity;
import com.capstone.timepay.domain.dealBoardComment.DealBoardComment;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardComment;
import com.capstone.timepay.domain.freeCommentReport.FreeCommentReport;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private Long fBoardId;
    private Long fCommentId;
    private Long dBoardId;
    private Long dCommentId;

    private Long uuid;
    private String content; // 작성한 댓글

    private String boardTitle; // 작성한 댓글의 게시글 제목
}
