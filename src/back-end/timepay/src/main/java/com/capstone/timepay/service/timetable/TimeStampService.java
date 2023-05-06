package com.capstone.timepay.service.timetable;

import com.capstone.timepay.domain.timetable.TimeStamp;
import com.capstone.timepay.domain.timetable.TimeStampRepository;
import com.capstone.timepay.domain.timetable.TimeTable;
import com.capstone.timepay.domain.timetable.TimeTableRepository;
import com.capstone.timepay.service.timetable.dto.TimeStampDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeStampService {

    private final TimeStampRepository timeStampRepository;
    private final TimeTableRepository timeTableRepository;

    public List<TimeStampDTO> findAllByWeekAndTimeTableId(Long timeTableId, String monday, String sunday) {
        List<TimeStamp> timeStampList = timeStampRepository.findAllByWeekAndTimeTableId(timeTableId, monday, sunday);
        return timeStampList.stream().map(TimeStampDTO::toTimeStampDTO).collect(Collectors.toList());
    }

    public TimeStampDTO addTimeStamp(TimeStampDTO timeStampDTO) {
        TimeTable timeTable = timeTableRepository.findById(timeStampDTO.getId()).orElseThrow(() ->
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
