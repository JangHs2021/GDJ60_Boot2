package com.iu.base.board.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.iu.base.member.MemberService;
import com.iu.base.member.MemberSocialService;
import com.iu.base.security.UserLoginFailHandler;
import com.iu.base.security.UserLogoutSuccessHandler;
import com.iu.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Bean
	WebSecurityCustomizer webSecurityConfig() {
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
					.cors()
//					.and()
//					.csrf()
					.disable()
			
				.authorizeRequests()
					// URL과 권한 매칭
					.antMatchers("/").permitAll()
					.antMatchers("/member/join").permitAll()
					.antMatchers("/notice/add").hasRole("ADMIN")
					.antMatchers("/notice/update").hasRole("ADMIN")
					.antMatchers("/notice/delete").hasRole("ADMIN")
					.antMatchers("/notice/**").permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/qna/add").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
					//.anyRequest().authenticated()
					.anyRequest().permitAll()
					.and()
				.formLogin()
					.loginPage("/member/login")
					// .defaultSuccessUrl("/")
					.successHandler(new UserSuccessHandler())
					// .failureUrl("/member/login")
					.failureHandler(new UserLoginFailHandler())
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")
					// .logoutSuccessUrl("/")
					.logoutSuccessHandler(new UserLogoutSuccessHandler())
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll()
					.and()
//				.sessionManagement()
//					.maximumSessions(1) // 최대 허용 가능한 session의 수, -1 : 무제한
//					.maxSessionsPreventsLogin(false) // false : 이전 사용자 session을 만료, true : 새로운 사용자 인증 실패
//					;
				.oauth2Login()
					.userInfoEndpoint()
					.userService(memberSocialService)
					;
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
