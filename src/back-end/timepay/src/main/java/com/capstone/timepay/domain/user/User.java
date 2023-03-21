package com.capstone.timepay.domain.user;

import com.capstone.timepay.domain.BaseTimeEntity;
import com.capstone.timepay.domain.dealBoardComment.DealBoardComment;
import com.capstone.timepay.domain.dealBoardReport.DealBoardReport;
import com.capstone.timepay.domain.dealRegister.DealRegister;
import com.capstone.timepay.domain.freeBoard.FreeBoard;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardComment;
import com.capstone.timepay.domain.freeBoardReport.FreeBoardReport;
import com.capstone.timepay.domain.freeRegister.FreeRegister;
import com.capstone.timepay.domain.inquiry.Inquiry;
import com.capstone.timepay.domain.userProfile.UserProfile;
import com.capstone.timepay.domain.userToken.UserToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column
    private String name;
    private String sex;
    private LocalDateTime birthday;
    private String location;
    private String phone;
    private String nickname;
    private String email;
    private boolean isBanned;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<DealBoardComment> dealBoardComments = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<DealRegister> dealRegisters = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<DealBoardReport> dealBoardReports = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<FreeBoardReport> freeBoardReports = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<FreeBoardComment> freeBoardComments = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<FreeRegister> freeRegisters = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name = "token_id")
    private UserToken userToken;

    public void updateName(String name) {
        this.name = name;
    }
    public void updateBirth(LocalDateTime birth) {
        this.birthday = birth;
    }
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updateLocation(String region) {
        this.location = region;
    }


}
