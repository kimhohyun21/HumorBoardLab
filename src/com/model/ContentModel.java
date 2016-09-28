package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardDTO;

public class ContentModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo=request.getParameter("no");
		String strPage=request.getParameter("page");
		
		BoardDAO dao=new BoardDAO();
		BoardDTO d=dao.boardContentData(Integer.parseInt(strNo));
		
		request.setAttribute("dto", d);
		request.setAttribute("page", strPage);
		
		return "humor/content.jsp";
	}

}
