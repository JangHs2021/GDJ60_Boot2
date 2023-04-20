package com.iu.base.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.base.board.BoardVO;

@SpringBootTest
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Test
	void setInsert() throws Exception {
		for(int i = 0; i < 100; i++) {
			QnaVO qnaVO = new QnaVO();
			
			qnaVO.setTitle("공지사항" + i);
			qnaVO.setWriter("작성자" + i);
			qnaVO.setContent("내용" + i);
			
			int result = qnaDAO.setInsert(qnaVO);
			
			if(i%10 == 0) {
				Thread.sleep(500);
			}
		}
		System.out.println("시스템 종료");
	}

}
