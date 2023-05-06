package com.capstone.timepay.service.timetable.dto;

import com.capstone.timepay.domain.timetable.TimeStamp;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeStampDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private String locate;
    private String content;
    private boolean isAdopted;

    static public TimeStampDTO toTimeStampDTO(TimeStamp timeStamp) {
        return TimeStampDTO.builder()
                .id(timeStamp.getId())
                .startTime(timeStamp.getStartTime())
                .endTime(timeStamp.getEndTime())
                .locate(timeStamp.getLocate())
                .content(timeStamp.getContent())
                .isAdopted(timeStamp.isAdopted())
                .build();
    }
}

