package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

public class UpdateModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String plist=request.getParameter("list");
		String strPage=request.getParameter("page");
		String strNo=request.getParameter("no");
		int no=Integer.parseInt(strNo);
		
		BoardDAO dao=new BoardDAO();
		BoardDTO dto=dao.boardContentData2(no);
		
		request.setAttribute("plist", plist);
		request.setAttribute("page", strPage);
		request.setAttribute("dto", dto);
		
		return "humor/update.jsp";
	}

}
