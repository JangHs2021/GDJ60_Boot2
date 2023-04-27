package com.iu.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO implements UserDetails {

	@NotBlank
	private String username;
	@NotBlank
	@Size(min = 4, max = 12)
	private String password;
	private String passwordCheck;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@Future
	private Date birth;

	private List<RoleVO> roleVOs;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return authorities;
	}
//	@Override
//	public String getPassword() {
//		password 반환
//		return null;
//	}
//	@Override
//	public String getUsername() {
//		username 반환
//		return null;
//	}
	@Override
	public boolean isAccountNonExpired() {
		// 계정의 만료 여부
		// true : 만료 안됨
		// false : 만료 됨, 로그인 x
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부
		// true : 잠김지 않음
		// false : 잠김, 로그인 x
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// password 만료 여부
		// true : 만료 안됨
		// false : 만료 됨, 로그인 x
		return true;
	}
	@Override
	public boolean isEnabled() {
		// 계정 사용 여부
		// true : 계정 활성화
		// false : 계정이 비활성화, 로그인 x
		return true;
	}
}
