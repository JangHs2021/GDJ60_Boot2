package com.iu.base.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = "join")
	public ModelAndView setMemberAdd() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/join");
		
		return mv;
	}
	
	@PostMapping(value = "join")
	public ModelAndView setMemberAdd(MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.setMemberAdd(memberVO);
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping(value = "login")
	public ModelAndView getMemberLogin(HttpServletRequest request)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/login");

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
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		} else {
			mv.setViewName("redirect:./login");
		}
		
		return mv;
	}
	
	@GetMapping(value = "logout")
	public ModelAndView getMemberLogout(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
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
	
	@GetMapping(value = "idDuplicateCheck")
	@ResponseBody
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception {
		memberVO = memberService.idDuplicateCheck(memberVO);
		
		log.debug("============== ID 중복 체크 ==============");
		
		boolean check = true;
		
		if(memberVO != null) {
			check = false;
		}
		
		return check;
	}
}
