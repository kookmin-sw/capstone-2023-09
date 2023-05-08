package com.capstone.timepay.domain.timetable;


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
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "timeTable", cascade = CascadeType.REMOVE)
    private List<TimeStamp> timeStamps = new ArrayList<>();
    private String monday;
    private String sunday;
}
