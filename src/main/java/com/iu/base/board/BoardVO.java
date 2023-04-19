package com.iu.base.board;

import java.sql.Date;
import java.util.List;

import com.iu.base.board.notice.NoticeFileVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
	
	private Long num;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Long hit;
	private List<BoardFileVO> boardFileVOs;
	
}
