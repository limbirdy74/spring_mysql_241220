package com.jbedu.mysql.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbedu.mysql.command.BCommand;
import com.jbedu.mysql.command.BListCommand;
import com.jbedu.mysql.command.BWriteCommand;
import com.jbedu.mysql.dao.BoardDao;
import com.jbedu.mysql.dto.BoardDto;

@Controller
public class BoardController {
	
	BCommand command = null;
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value = "/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {
		
//		String bname = request.getParameter("bname");
//		String btitle = request.getParameter("btitle");
//		String bcontent = request.getParameter("bcontent");
//		
//		// command 에서 해야 하나 그냥 해보자
//		
//		BoardDao boardDao = new BoardDao();
//		boardDao.boardWrite(bname, btitle, bcontent);
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.execute(model);	
		
		return "redirect:boardList";  // 글이 작성되고 난후 모든 글의 리스트를 보여주는 요청을 새로 보내주어야 함. redirect
	}
	
	@RequestMapping(value = "/boardList")
	public String boardList(HttpServletRequest request, Model model) {
		
//		BoardDao boardDao = new BoardDao();
//		ArrayList<BoardDto> bDtos = boardDao.boardList();
//		
//		model.addAttribute("bDtos", bDtos);
//		
		command = new BListCommand();
		command.execute(model);
		
		
		return "boardList";
	}
	
	@RequestMapping(value = "/delete_form")
	public String delete_form() {
		return "delete_form";
	}
	
	@RequestMapping(value = "/deleteOk")
	public String deleteOk(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum");
		
		// command 에서 해야 하나 그냥 해보자
		
		BoardDao boardDao = new BoardDao();
		int deleteFlag = boardDao.boardDelete(bnum); // 성공 1, 실패 0
		
		if (deleteFlag != 1) {  // 존재하지 않는 글번호 삭제 실패
			
			model.addAttribute("msg", "이미 삭제된 글번호 입니다");
			model.addAttribute("url", "boardList");
			return "alert";  
		} 
//		else {
//			return "redirect:boardList"; 
//		}
		// return 은 한번 실행되면 자바가 끝나므로 else를 안써도 된다
		
		return "redirect:boardList"; 
	}
	
	@RequestMapping(value = "/alert")
	public String alert() {
		return "alert";
	}
}
