package com.capstone.timepay.service.timetable;

import com.capstone.timepay.domain.timetable.TimeStamp;
import com.capstone.timepay.domain.timetable.TimeStampRepository;
import com.capstone.timepay.domain.timetable.TimeTable;
import com.capstone.timepay.domain.timetable.TimeTableRepository;
import com.capstone.timepay.service.timetable.dto.TimeStampDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeStampService {

    private final TimeStampRepository timeStampRepository;
    private final TimeTableRepository timeTableRepository;

    public List<TimeStampDTO> getTimeTable(Long id, String monday, String sunday) {
        TimeTable timeTable = timeTableRepository.findById(id).orElse(null);
        if (timeTable == null) {
            List<TimeStamp> timeStamp = new ArrayList<>();
            // timeTable이 null인 경우에 대한 처리
            timeTable = TimeTable.builder()
                    .id(id)
                    .timeStamps(timeStamp)
                    .monday(monday)
                    .sunday(sunday)
                    .build();
            timeTableRepository.save(timeTable);
        }
        List<TimeStamp> timeStampList = timeStampRepository.findAllByWeekAndTimeTableId(id, monday, sunday);
        return timeStampList.stream().map(TimeStampDTO::toTimeStampDTO).collect(Collectors.toList());
    }

    public TimeStampDTO addTimeStamp(Long id, TimeStampDTO timeStampDTO) {
        TimeTable timeTable = timeTableRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid timeTableId"));

        TimeStamp timeStamp = TimeStamp.builder()
                .timeTable(timeTable)
                .startTime(timeStampDTO.getStartTime())
                .endTime(timeStampDTO.getEndTime())
                .locate(timeStampDTO.getLocate())
                .content(timeStampDTO.getContent())
                .isAdopted(false)
                .build();
        TimeStamp savedTimeStamp = timeStampRepository.save(timeStamp);
        return TimeStampDTO.toTimeStampDTO(savedTimeStamp);
    }

    public void deleteTimeStamp(Long id) {
        timeStampRepository.deleteById(id);
    }
}
