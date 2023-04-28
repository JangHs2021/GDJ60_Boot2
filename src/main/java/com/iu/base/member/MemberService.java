package com.iu.base.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 패스워드가 일치하는지 검증하는 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false;
		
		result = bindingResult.hasErrors();
		
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			result = true;
			bindingResult.rejectValue("passwordCheck", "member.passWord.notEqual");
		}
		
		MemberVO idcheck = memberDAO.idDuplicateCheck(memberVO);
		
		if(idcheck != null) {
			result = true;
			bindingResult.rejectValue("username", "member.userName.Equal");
		}
		return result;
	}
	
	public int setMemberAdd(MemberVO memberVO) throws Exception {
//		memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		
		int result = memberDAO.setMemberAdd(memberVO);
		
		result = memberDAO.setMemberRoleAdd(memberVO);
		
		return result;
	}
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception {
		
//		MemberVO result = memberDAO.getMemberLogin(memberVO);
//		
//		if(memberVO != null && memberVO.getPassWord().equals(result.getPassWord())) {
//			 memberVO.setPassWord(null);
//			 memberVO.getRoleVOs(result.getRoleVOs());
//			 return memberVO;
//		} else {
//			return null;
//		}
		return memberDAO.getMemberLogin(memberVO);
	}
	
	public MemberVO getMemberPage(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogin(memberVO);
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
	public int getMemberLogout(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogout(memberVO);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.error("=================== Spring Security Login ==================");
		log.error("=================== {} =================", username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getMemberLogin(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}
}
