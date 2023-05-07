package com.capstone.timepay.service.timetable;

import com.capstone.timepay.domain.timetable.TimeStamp;
import com.capstone.timepay.domain.timetable.TimeStampRepository;
import com.capstone.timepay.domain.timetable.TimeTable;
import com.capstone.timepay.domain.timetable.TimeTableRepository;
import com.capstone.timepay.service.timetable.dto.TimeStampDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
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

    public TimeStampDTO getOneTimeStamp(Long id, Long timeStampId)
    {
        List<TimeStamp> timeStamps = timeTableRepository.findById(id).get().getTimeStamps();
        TimeStamp getOne = new TimeStamp();
        if (timeStamps.isEmpty())
        {
            throw new IllegalArgumentException("타임스탬프가 존재하지 않습니다");
        }

        for (TimeStamp timeStamp : timeStamps)
        {
            if (timeStamp.getId() == timeStampId) {
                getOne = timeStamp;
                break;
            }
        }

        if (getOne == null)
        {
            throw new IllegalArgumentException("타임스탬프가 존재하지 않습니다");
        }
        return TimeStampDTO.toTimeStampDTO(getOne);
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

    public List<TimeStampDTO> getHelper(String monday, String sunday)
    {
        List<TimeStamp> timeStampList = timeStampRepository.findAllByWeek(monday, sunday);
        int size = timeStampList.size();


        // 랜덤한 값 넣기
        List<TimeStampDTO> randomTimeStamp = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            Long num = new Random().nextLong(size);
            TimeStamp tmpTimeStamp = timeStampRepository.findById(num).orElse(null);
            randomTimeStamp.add(TimeStampDTO.toTimeStampDTO(tmpTimeStamp));
        }
        return randomTimeStamp;
    }

    public TimeStampDTO getHelperStamp(Long timeStampId)
    {
        TimeStamp timeStamp = timeStampRepository.findById(timeStampId).orElseThrow(() -> {
            return new IllegalArgumentException("타임스탬프가 존재하지 않습니다");
        });
        return TimeStampDTO.toTimeStampDTO(timeStamp);
    }

    public HttpStatus setApply(Long timeStampId)
    {
        TimeStamp timeStamp = timeStampRepository.findById(timeStampId).orElseThrow(() -> {
            return new IllegalArgumentException("타임스탬프가 존재하지 않습니다");
        });
        timeStamp.setAdopted(true);
        return HttpStatus.OK;
    }
}
