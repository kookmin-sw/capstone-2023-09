package com.capstone.timepay.domain.timetable;

import com.capstone.timepay.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_table_id")
    private TimeTable timeTable;
    private String startTime;
    private String endTime;
    private String locate;
    private String content;
    private boolean isAdopted;
}

// 일단 임시
//    @OneToMany(mappedBy = "timeStamp", orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<User> participants = new ArrayList<>();
