package com.iu.base.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public int setMemberAdd(MemberVO memberVO) throws Exception;
	
	public int setMemberRoleAdd(MemberVO memberVO) throws Exception;
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception;
	
	public List<MemberVO> getMembers() throws Exception;
	
	public int getMemberLogout(MemberVO memberVO) throws Exception;
	
	public int setEnabled() throws Exception;
	
	public List<MemberVO> getBirth() throws Exception;
}
