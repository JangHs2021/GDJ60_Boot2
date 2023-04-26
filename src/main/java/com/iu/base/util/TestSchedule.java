package com.iu.base.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;

	@Scheduled(cron = "10 * * * * *")
	public void test() throws Exception {
		log.error("============ 반복중 ============");
		
		List<MemberVO> ar = memberDAO.getBirth();
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("이달의 생일");
		boardVO.setWriter("관리자");
		
		boardVO.setContent("생일 축하 합니다" + ar.get(0).getName() + "," + ar.get(1).getName());
		
		int result = noticeDAO.setInsert(boardVO);
	}
}
