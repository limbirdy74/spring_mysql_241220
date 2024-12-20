package com.jbedu.mysql.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jbedu.mysql.dao.BoardDao;

public class BDeleteCommand implements BCommand {

	@Override
	public int execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap(); // model 내에 실어준 문자열이름(key)와 값(value)가 map에 저장
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");  // model 내의 값 가져오기		
		
		BoardDao boardDao = new BoardDao();
		int deleteFlag = boardDao.boardDelete(request.getParameter("bnum")); // 성공 1, 실패 0
		
		return deleteFlag;
	}

}
