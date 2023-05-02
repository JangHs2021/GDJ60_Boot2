package com.iu.base.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
			
		log.error("================ logout Success Handler =============");
		
		// response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+restKey+"&logout_redirect_uri=http://localhost/");
	}
	
//	private void simpleLogout() {
//		RestTemplate restTemplate = new RestTemplate();
//		
//		// header
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "KakaoAK"+adminKey);
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//	}
}
