package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardDTO;

public class ReplyOkModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String strPage=request.getParameter("page");
		String strNo=request.getParameter("no");
		int no=Integer.parseInt(strNo);
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		//DTO에 모아서 전송
		BoardDTO dto=new BoardDTO();
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		//DAO를 에서 메서드 호출
		BoardDAO dao=new BoardDAO();
		dao.boardReply(no, dto);
		
		return "list.do?page="+strPage;
	}

}
