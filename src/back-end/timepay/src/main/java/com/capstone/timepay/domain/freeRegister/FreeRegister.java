package com.capstone.timepay.domain.freeRegister;

import com.capstone.timepay.domain.BaseTimeEntity;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.domain.user.User;
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
public class FreeRegister extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long f_registerId;

    @ManyToOne
    @JoinColumn(name="free_board_id")
    private FreeBoard freeBoard;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
