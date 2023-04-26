package com.iu.base.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	// 패스워드가 일치하는지 검증하는 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false;
		
		result = bindingResult.hasErrors();
		
		if(!memberVO.getPassWord().equals(memberVO.getPassWordCheck())) {
			result = true;
			bindingResult.rejectValue("passWordCheck", "member.passWord.notEqual");
		}
		
		MemberVO idcheck = memberDAO.idDuplicateCheck(memberVO);
		
		if(idcheck != null) {
			result = true;
			bindingResult.rejectValue("userName", "member.userName.Equal");
		}
		return result;
	}
	
	public int setMemberAdd(MemberVO memberVO) throws Exception {
		memberVO.setEnabled(true);
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
}
