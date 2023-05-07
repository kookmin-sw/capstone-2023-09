package com.capstone.timepay.controller.timetable;

import com.capstone.timepay.domain.timetable.TimeStamp;
import com.capstone.timepay.service.timetable.TimeStampService;
import com.capstone.timepay.service.timetable.dto.TimeStampDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/timetable")
public class TimeTableController
{
    private final TimeStampService timeStampService;

    @ApiOperation(value = "도움받기 페이지 조회")
    @GetMapping("")
    public List<TimeStampDTO> getTimeStamp(@RequestParam Long id,
                                           @RequestParam String monday,
                                           @RequestParam String sunday)
    {
        return timeStampService.getTimeTable(id, monday, sunday);
    }

    @ApiOperation(value = "도움받기 시간표 추가")
    @PostMapping("/help/add/{id}")
    public ResponseEntity<TimeStampDTO> addTimeStamp(@RequestBody @PathVariable("id") Long id,
                                                     @RequestBody TimeStampDTO timeStampDTO) {
        return new ResponseEntity<>(timeStampService.addTimeStamp(id, timeStampDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "도움받기 시간표 상세조회")
    @GetMapping("/{id}/{timeStampId}")
    public TimeStampDTO getOneTimeStamp(@PathVariable("id") Long id,
                                        @PathVariable("timeStampId") Long timeStampId)
    {
        return timeStampService.getOneTimeStamp(id, timeStampId);
    }

    @ApiOperation(value = "도움받기 시간표 삭제")
    @DeleteMapping("/help/delete/{id}")
    public void deleteTimeStamp(@PathVariable("id") Long id) {
        timeStampService.deleteTimeStamp(id);
    }

    @ApiOperation(value = "도움주기 페이지 조회")
    @GetMapping("/helper")
    public List<TimeStampDTO> getHelper(@RequestParam String monday,
                                        @RequestParam String sunday)
    {
        return timeStampService.getHelper(monday, sunday);
    }
}
