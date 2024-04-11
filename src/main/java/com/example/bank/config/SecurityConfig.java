package com.example.bank.config;

import com.example.bank.domain.user.UserEnum;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        log.debug("디버그: BCryptPasswordEncoder 빈 등록됨");
        return new BCryptPasswordEncoder();
    }

    //JWT 서버를 만들 예정 !! Session 사용안함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.headers().frameOptions().disable(); // iframe 허용안함
        http.csrf().disable(); // enable이면 post면 작동안함
        http.cors().configurationSource(corsConfigurationSource());

        //jsessionId를 서버쪽에서 관리안하겠다는 뜻!!
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //react, 앱으로 요청할 예정
        http.formLogin().disable();
        //httpBasic은 브라우저가 팝업창을 이용해서 사용자 인증을 진행한다
        http.httpBasic().disable();

        http.authorizeHttpRequests()
                .antMatchers("/api/s/**").authenticated()
                .antMatchers("/api/admin/**").hasRole(""+ UserEnum.ADMIN)
                .anyRequest().permitAll();

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource(){
        log.debug("디버그: corsConfigurationSource 빈 등록됨");
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); //get,post,put,delete
        configuration.addAllowedOriginPattern("*"); //모든 ip주소 허용(프론트엔드 ip만 허용)
        configuration.setAllowCredentials(true); //클라이언트에서 쿠키 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration); // 위에 설정을 모든주소 넣어준다는거다
        return source;
    }
}


