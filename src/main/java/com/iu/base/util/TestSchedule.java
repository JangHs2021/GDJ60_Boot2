package com.iu.base.util;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

@Component
public class TestSchedule {
	
	@Autowired
	private MemberDAO memberDAO;
	
//	@Autowired
//	private NoticeDAO noticeDAO;
	
	@Autowired
	private EmailService emailService;

//	@Scheduled(cron = "10 * * * * *")
	public void birthTest() throws Exception {
		
		List<MemberVO> ar = memberDAO.getBirth();
//		
//		BoardVO boardVO = new BoardVO();
//		boardVO.setTitle("이달의 생일");
//		boardVO.setWriter("관리자");
//		
//		boardVO.setContent("생일 축하 합니다" + ar.get(0).getName() + "," + ar.get(1).getName());
//		
//		int result = noticeDAO.setInsert(boardVO);
		
		for(MemberVO memberVO : ar) {
			emailService.sendEmail(memberVO.getEmail(), "생일 축하 합니다", "<h1>생일 축하</h1>");
		}
	}
}
