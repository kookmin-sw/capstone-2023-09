package com.capstone.timepay.controller.timetable;

import com.capstone.timepay.domain.timetable.TimeStamp;
import com.capstone.timepay.service.timetable.TimeStampService;
import com.capstone.timepay.service.timetable.dto.TimeStampDTO;
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

    @GetMapping("")
    public List<TimeStampDTO> getTimeStamp(@RequestParam Long id,
                                           @RequestParam String monday,
                                           @RequestParam String sunday)
    {
        return timeStampService.getTimeTable(id, monday, sunday);
    }

    @PostMapping("/help/add/{id}")
    public ResponseEntity<TimeStampDTO> addTimeStamp(@RequestBody @PathVariable("id") Long id,
                                                     @RequestBody TimeStampDTO timeStampDTO) {
        return new ResponseEntity<>(timeStampService.addTimeStamp(id, timeStampDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/help/delete/{id}")
    public void deleteTimeStamp(@PathVariable("id") Long id) {
        timeStampService.deleteTimeStamp(id);
    }

}
