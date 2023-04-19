package com.iu.base.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping(value = "list")
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("search : {}", pager.getSearch());
		log.info("kind : {}", pager.getKind());
		
		List<BoardVO> ar = noticeService.getList(pager);
		
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@GetMapping(value = "add")
	public ModelAndView setInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/add");
		
		return mv;
	}
	
	@PostMapping(value = "add")
	public ModelAndView setInsert(NoticeVO noticeVO, MultipartFile [] boardFiles) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		for(MultipartFile multipartFile : boardFiles) {
			log.info("OriName : {} Size : {}", multipartFile.getOriginalFilename(), multipartFile.getSize());
		}
		
		int result = noticeService.setInsert(noticeVO, boardFiles);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@GetMapping(value = "detail")
	public ModelAndView getDetail(NoticeVO noticeVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		noticeVO = (NoticeVO)noticeService.getDetail(noticeVO);
		
		mv.addObject("boardVO", noticeVO);
		mv.setViewName("board/detail");
		
		return mv;
	}
	
	@GetMapping(value = "fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		
		mv.addObject("boardFileVO", boardFileVO);
		mv.setViewName("fileManager");
		
		return mv;
	}
}
