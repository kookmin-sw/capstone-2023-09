package com.capstone.timepay.controller.admin;

import com.capstone.timepay.controller.admin.request.comment.CommentSearchRequest;
import com.capstone.timepay.controller.admin.request.report.PenaltyUserRequest;
import com.capstone.timepay.controller.admin.request.report.ReportSearchRequest;
import com.capstone.timepay.controller.admin.response.comment.CommentResponse;
import com.capstone.timepay.controller.admin.response.report.ReportResponse;
import com.capstone.timepay.service.admin.ReportManageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins/reports")
public class ReportManageController {

    private final ReportManageService reportManageService;

    @ApiOperation(value = "전체 신고 리스트 조회")
    @GetMapping("/main")
    public ResponseEntity<?> main(@RequestParam(defaultValue = "0") int pageIndex,
                                  @RequestParam(defaultValue = "50") int pageSize){

        Page<ReportResponse> responses = reportManageService.showAllReports(pageIndex, pageSize);

        return ResponseEntity.ok(responses);
    }

    @ApiOperation(value = "신고 검색 조회")
    @GetMapping(value = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> search(@RequestBody ReportSearchRequest request){

        Page<ReportResponse> responses = reportManageService.showReportsBySearch(request.toServiceDto());

        return ResponseEntity.ok(responses);
    }

    @ApiOperation(value = "신고 회원 리스트 제재 처리")
    @PatchMapping(value = "/penalty", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> penalty(@RequestBody PenaltyUserRequest request){

        reportManageService.penaltyUsers(request.toServiceDto());

        return ResponseEntity.ok("제재 처리 되었습니다");
    }
}
