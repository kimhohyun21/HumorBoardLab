package com.model;

import javax.servlet.http.*;
import com.dao.*;

public class ContentModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("EUC-KR");
		String strNo = request.getParameter("no");
		String strPage = request.getParameter("page");
		String plist = request.getParameter("list");
		String fs=request.getParameter("fs");
		String fi=request.getParameter("fi");
		
		BoardDAO dao = new BoardDAO();
		//boardContentData의 String 값을 Int 값으로 변환
		BoardDTO dto = dao.boardContentData(Integer.parseInt(strNo));
		
		request.setAttribute("page", strPage);
		request.setAttribute("plist", plist);
		request.setAttribute("dto", dto);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		return "humor/content.jsp";
	}
}
