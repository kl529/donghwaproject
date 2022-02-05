package com.liverary.book.springboot.config.auth;

import com.liverary.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers("/","/css/**","/images/**","/js/**", "h2-console/**").permitAll()
=======
//                .antMatchers("/","/css/**","/images/**","/js/**", "h2-console/**").permitAll()
>>>>>>> origin/yes
//                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())    // 책 등록 및 삭제를 위한 주소는 /admin/**로 만들자
//                .anyRequest().authenticated()   // 이외 페이지는 로그인 사용자만 허용
                .anyRequest().permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
