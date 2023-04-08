package com.capstone.timepay.domain.board;

import com.capstone.timepay.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private Long uuid;
    private String title;
    private String category; // 이 글이 자유게시판인지 거래게시판인지

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

}
