package com.capstone.timepay.service.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeleteNotificationDTO {
    private List<String> ids;
}
