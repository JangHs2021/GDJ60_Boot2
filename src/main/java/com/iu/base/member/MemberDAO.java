package com.iu.base.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public int setMemberAdd(MemberVO memberVO) throws Exception;
	
	public int setMemberRoleAdd(MemberVO memberVO) throws Exception;
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception;
}
