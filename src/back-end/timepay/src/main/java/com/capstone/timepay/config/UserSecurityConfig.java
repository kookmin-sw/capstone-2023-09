package com.capstone.timepay.config;

import com.capstone.timepay.filter.JwtFilter;
import com.capstone.timepay.service.admin.AdminDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// @Order(0)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_URI = {
            "/api/admins/login",
            "/api/admins/register",
            "/api/users/create",
            "/api/users/test/**",
            "/login",
            "/api/users/delete/**", // 테스트용
    };

    private static final String[] TIMEPAY_URI = {
            "/api/deal-boards/comments/**/**/report",
            "/api/free-boards/comments/**/**/report",
            "/api/deal-boards/**/report",
            "/api/free-boards/**/report",
    };

    @Autowired
    private AdminDetailService adminDetailService;

    @Autowired
    private JwtFilter adminJwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* 아래 configure를 하기 전에 먼저 실행되는 함수 */
    /* PUBLIC_URI 접근에 대한 것들은 검증하지 않기 때문에 ignoring */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(PUBLIC_URI);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 세션 사용 X
                .authorizeRequests() // 요청에 대한 사용 권한 체크
                .antMatchers("/api/admins/**").hasRole("ADMIN")
                .antMatchers("/api/users/**").hasRole("USER")
                .antMatchers(TIMEPAY_URI).hasAnyRole("USER", "ADMIN")
                // .antMatchers(TIMEPAY_URI).access("hasRole('USER') or hasRole('ADMIN')")
                // .anyRequest().permitAll() 그외 모든 요청 접근 가능, 위 configure을 오버라이딩하여 깔끔하게 해결함
                .antMatchers(HttpMethod.POST, "/api/notifications/**").authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

        http.addFilterBefore(adminJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
