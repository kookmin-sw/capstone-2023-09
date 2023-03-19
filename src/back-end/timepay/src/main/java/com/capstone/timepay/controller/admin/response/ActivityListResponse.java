package com.capstone.timepay.controller.admin.response;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityListResponse {

    private BoardActivityDto boardActivity;
    private CommentActivityDto commentActivity;
    private  ReceivedReportDto receivedReport;
    private  ReportDto report;

}
