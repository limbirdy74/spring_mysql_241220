package com.jbedu.mysql.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.jbedu.mysql.dao.BoardDao;
import com.jbedu.mysql.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public int execute(Model model) {
		// TODO Auto-generated method stub
		
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> bDtos = boardDao.boardList();
		
		model.addAttribute("bDtos", bDtos);
		return 0;
	}

}
