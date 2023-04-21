package com.capstone.timepay.controller.admin.response.auth;

import com.capstone.timepay.domain.admin.Admin;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoResponse {

    private Long adminId;   // 식별자

    private String adminName;   // 로그인 할 때 쓰는 아이디
    private String password;
    private String authority;
    private String name;    // 관리자 이름
    private String email;
    private String phone;

    private List<String> roles;

    public AdminInfoResponse(Admin admin) {
        this.adminId = admin.getAdminId();
        this.adminName = admin.getAdminName();
        this.password = admin.getPassword();
        this.authority = admin.getAuthority();
        this.name = admin.getName();
        this.email = admin.getEmail();
        this.phone = admin.getPhone();
        this.roles = admin.getRoles();
    }

}
