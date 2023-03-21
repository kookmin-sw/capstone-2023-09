package com.capstone.timepay.service.admin;

import com.capstone.timepay.controller.admin.response.inquiry.AdminAnswerResponse;
import com.capstone.timepay.controller.admin.response.inquiry.InquiryDetailResponse;
import com.capstone.timepay.controller.admin.response.inquiry.InquiryResponse;
import com.capstone.timepay.domain.admin.Admin;
import com.capstone.timepay.domain.admin.AdminRepository;
import com.capstone.timepay.domain.inquiry.Inquiry;
import com.capstone.timepay.domain.inquiry.InquiryRepository;
import com.capstone.timepay.domain.inquiryAnswer.InquiryAnswer;
import com.capstone.timepay.domain.inquiryAnswer.InquiryAnswerRepository;
import com.capstone.timepay.service.admin.dto.InquiryAnswerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryManagerService {

    private final InquiryRepository inquiryRepository;
    private final AdminRepository adminRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;


    public List<InquiryResponse> showAllInquiries() {
        List<Inquiry> inquiries = inquiryRepository.findAll();

        return inquiries.stream()
                        .map(inquiry -> InquiryResponse.builder()
                                .inquiryId(inquiry.getInquiryId())
                                .state(inquiry.getState())
                                .category(inquiry.getCategory())
                                .createdAt(inquiry.getCreatedAt())
                                .writer(inquiry.getUser().getName())
                                .title(inquiry.getTitle())
                                .build())
                        .sorted(Comparator.comparing(InquiryResponse::getCreatedAt).reversed())
                        .collect(Collectors.toList());
    }

    public List<InquiryResponse> searchInquiriesByQuery(String state, String category, String writer, String title) {

        List<Inquiry> inquiries = new ArrayList<>();

        if(state.equals("all") && category.equals("all")){
            inquiries = inquiryRepository.findAll();
        }
        else if(state.equals("all")){
            inquiries = inquiryRepository.findAllByCategory(category).orElseThrow(()->new IllegalArgumentException("존재하지 않는 문의입니다."));
        }
        else if(category.equals("all")){
            inquiries = inquiryRepository.findAllByState(state).orElseThrow(()->new IllegalArgumentException("존재하지 않는 문의입니다."));
        }
        else{
            inquiries = inquiryRepository.findAllByCategoryAndState(category, state).orElseThrow(()->new IllegalArgumentException("존재하지 않는 문의입니다."));
        }

        return inquiries.stream()
                        .map(inquiry -> InquiryResponse.builder()
                                .inquiryId(inquiry.getInquiryId())
                                .title(inquiry.getTitle())
                                .category(inquiry.getCategory())
                                .writer(inquiry.getUser().getName())
                                .state(inquiry.getState())
                                .createdAt(inquiry.getCreatedAt())
                                .build())
                        .filter(inquiryResponse -> inquiryResponse.getWriter().equals(writer) && !writer.equals("all"))
                        .filter(inquiryResponse -> inquiryResponse.getTitle().contains(title) && !title.equals("all"))
                        .sorted(Comparator.comparing(InquiryResponse::getCreatedAt).reversed())
                        .collect(Collectors.toList());
    }


    public void saveInquiryAnswer(InquiryAnswerDto answer) {

        Inquiry inquiry = inquiryRepository.findById(answer.getInquiryId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 문의입니다."));
        Admin admin = adminRepository.findById(answer.getAdminId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 관리자입니다."));

        InquiryAnswer inquiryAnswer = InquiryAnswer.getNewInstance(answer, inquiry, admin);

        inquiryAnswerRepository.save(inquiryAnswer);
    }

    public InquiryDetailResponse showInquiryDetail(Long inquiryId) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 문의입니다."));
        List<InquiryAnswer> inquiryAnswers = inquiryAnswerRepository.findAllByInquiry(inquiry).orElseThrow(()->new IllegalArgumentException("존재하지 않는 문의 답변입니다."));

        InquiryResponse inquiryResponse =
                InquiryResponse.builder()
                                .inquiryId(inquiry.getInquiryId())
                                .state(inquiry.getState())
                                .category(inquiry.getCategory())
                                .createdAt(inquiry.getCreatedAt())
                                .writer(inquiry.getUser().getName())
                                .title(inquiry.getTitle())
                                .build();
        List<AdminAnswerResponse> adminAnswerResponses =
                inquiryAnswers.stream()
                                .map(inquiryAnswer -> AdminAnswerResponse.builder()
                                        .content(inquiryAnswer.getContent())
                                        .adminName(inquiryAnswer.getAdmin().getAdminName())
                                        .createdAt(inquiryAnswer.getCreatedAt())
                                        .build())
                                .collect(Collectors.toList());

        return new InquiryDetailResponse(inquiryResponse,adminAnswerResponses);

    }
}
