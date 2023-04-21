package com.iu.base.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	public int setMemberAdd(MemberVO memberVO) throws Exception {
		
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
}
