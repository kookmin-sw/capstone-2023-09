package com.capstone.timepay.domain.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeStampRepository extends JpaRepository<TimeStamp, Long> {
    @Query("SELECT t FROM TimeStamp t WHERE t.timeTable.id = :timeTableId AND t.startTime >= :monday AND t.endTime <= :sunday")
    List<TimeStamp> findAllByWeekAndTimeTableId(@Param("timeTableId") Long timeTableId, @Param("monday") String monday, @Param("sunday") String sunday);

    @Query("SELECT t FROM TimeStamp t WHERE t.startTime >= :monday AND t.endTime <= :sunday")
    List<TimeStamp> findAllByWeek(@Param("monday") String monday, @Param("sunday") String sunday);

}
