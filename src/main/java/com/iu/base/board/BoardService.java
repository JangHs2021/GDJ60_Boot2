package com.iu.base.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iu.base.board.qna.QnaVO;
import com.iu.base.util.Pager;

public interface BoardService {
	
	// 글조회
	public List<BoardVO> getList(Pager pager) throws Exception;
		
	// 글 하나 조회
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
		
	// 글쓰기
	public int setInsert(BoardVO boardVO, MultipartFile [] multipartFiles) throws Exception;
		
	// 글수정
	public int setUpdate(BoardVO boardVO) throws Exception;
		
	// 글삭제
	public int setDelete(BoardVO boardVO) throws Exception;
	
	// 파일조회
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception;
}
