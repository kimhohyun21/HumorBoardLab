package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

public class UpdateModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("EUC-KR");
		String plist=request.getParameter("list");
		String strPage=request.getParameter("page");
		String strNo=request.getParameter("no");
		int no=Integer.parseInt(strNo);
		String fs=request.getParameter("fs");
		String fi=request.getParameter("fi");
		
		BoardDAO dao=new BoardDAO();
		BoardDTO dto=dao.boardContentData2(no);
		
		request.setAttribute("plist", plist);
		request.setAttribute("page", strPage);
		request.setAttribute("dto", dto);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		return "humor/update.jsp";
	}

}
