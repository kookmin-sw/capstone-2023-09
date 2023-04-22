package com.capstone.timepay.domain.board;

import com.capstone.timepay.domain.BaseTimeEntity;
import com.capstone.timepay.domain.dealBoard.DealBoard;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.domain.user.User;
import com.capstone.timepay.domain.userProfile.UserProfile;
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

    @OneToOne
    @JoinColumn(name = "f_boardId")
    private FreeBoard freeBoard;

    @OneToOne
    @JoinColumn(name = "d_boardId")
    private DealBoard dealBoard;
}
