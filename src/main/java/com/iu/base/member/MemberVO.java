package com.iu.base.member;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {

	@NotBlank
	private String userName;
	@NotBlank
	@Size(min = 4, max = 12)
	private String passWord;
	
	private String passWordCheck;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@Future
	private Date birth;
	private boolean enabled;
	private List<RoleVO> roleVOs;
}
