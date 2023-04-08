package com.capstone.timepay.domain.board;

import com.capstone.timepay.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private Long uuid;
    private String title;
    private int timePay;
    private String category; // 이 글이 자유게시판인지 거래게시판인지

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

}
