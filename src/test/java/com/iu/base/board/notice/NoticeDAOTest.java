package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.base.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
//	@Test
//	void setInsert() throws Exception {
//		for(int i = 0; i < 100; i++) {
//			BoardVO boardVO = new BoardVO();
//			
//			boardVO.setTitle("공지사항" + i);
//			boardVO.setWriter("작성자" + i);
//			boardVO.setContent("내용" + i);
//			
//			int result = noticeDAO.setInsert(boardVO);
//			
//			if(i%10 == 0) {
//				Thread.sleep(500);
//			}
//		}
//		System.out.println("시스템 종료");
//	}
	
//	@Test
//	void setUpdate() throws Exception {
//		BoardVO boardVO = new BoardVO();
//		
//		boardVO.setNum(2L);
//		boardVO.setTitle("공지사항2");
//		boardVO.setContent("내용2");
//		
//		int result = noticeDAO.setUpdate(boardVO);
//		
//		assertEquals(1, result);
//	}
	
//	@Test
//	void setDelete() throws Exception {
//		BoardVO boardVO = new BoardVO();
//		
//		boardVO.setNum(1L);
//		
//		int result = noticeDAO.setDelete(boardVO);
//		
//		assertEquals(1, result);
//	}
	
//	@Test
//	void getList() throws Exception {
//		List<BoardVO> ar = noticeDAO.getList();
//
//		assertNotEquals(0, ar.size());
//	}
}
