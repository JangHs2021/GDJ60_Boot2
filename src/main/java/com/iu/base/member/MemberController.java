package com.iu.base.member;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "info")
	public void info(HttpSession session) {
		String pw = "qwer1234";
		MemberVO memberVO = (MemberVO) memberService.loadUserByUsername("wkdgustj02");
		
		log.error("============== {} ============", memberVO.getPassword());
		log.error("============== {} ============", passwordEncoder.encode(pw));
		log.error("============== {} ============", memberVO.getPassword().equals(passwordEncoder.encode(pw)));
		
		// encode 된 패스워드는 passwordEncoder의 matches로 DB에 암호화된 패스워드와 비교
		boolean check = passwordEncoder.matches(pw, memberVO.getPassword());
		log.error("============== {} ============", check);
		
		log.error("================== Login Info =================");
//		Enumeration<String> names = session.getAttributeNames();
//		while(names.hasMoreElements()) {
//			log.error("======== {} =======", names.nextElement());
//		}
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
		Authentication authentication = contextImpl.getAuthentication();
		
		log.error("======== {} =======", obj);
		log.error("======== NAME : {} =======", authentication.getName());
		log.error("======== DETAIL : {} =======", authentication.getDetails());
		log.error("======== PRINCIPAL : {} =======", authentication.getPrincipal());
	}
	
	@GetMapping(value = "join")
	public ModelAndView setMemberAdd(@ModelAttribute MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/join");
		
		return mv;
	}
	
	@PostMapping(value = "join")
	public ModelAndView setMemberAdd(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		
		if(check) {
			mv.setViewName("member/join");
			return mv;
		}
		
		int result = memberService.setMemberAdd(memberVO);
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping(value = "login")
	public ModelAndView getMemberLogin(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		Object object = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(object == null) {
			mv.setViewName("member/login");
		} else {
			mv.setViewName("redirect:/");
		}

		return mv;
	}
	
	@PostMapping(value = "login")
	public ModelAndView getMemberLogin(MemberVO memberVO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
//		if(remember != null && remember.equals("remember")) {
//			Cookie cookie = new Cookie("rememberId", memberVO.getUserName());
//			cookie.setMaxAge(-1);
//			response.addCookie(cookie);	
//		} else {
//			Cookie cookie = new Cookie("rememberId", "");
//			cookie.setMaxAge(0);
//			response.addCookie(cookie);
//		}
		
		memberVO = memberService.getMemberLogin(memberVO);
		mv.setViewName("redirect:./login");
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		
		return mv;
	}
	
	@GetMapping(value = "logout")
	public ModelAndView getMemberLogout(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		int result = memberService.getMemberLogout(memberVO);
		
		session.invalidate();
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping(value = "mypage")
	public ModelAndView getMemberPage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		memberVO = memberService.getMemberPage(memberVO);
		
		mv.addObject("memberVO", memberVO);
		mv.setViewName("member/mypage");
		
		return mv;
	}
	
	@GetMapping(value = "admin")
	public void getAdmin() throws Exception {
	}
	
	@GetMapping(value = "idDuplicateCheck")
	@ResponseBody
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception {
		memberVO = memberService.idDuplicateCheck(memberVO);
		
		boolean check = true;
		
		if(memberVO != null) {
			check = false;
		}
		return check;
	}
	
	
}
