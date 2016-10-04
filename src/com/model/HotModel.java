package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardDTO;

public class HotModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao=new BoardDAO();
		
		request.setCharacterEncoding("UTF-8");
		String strNo=request.getParameter("no");
		String strPage=request.getParameter("page");
		String plist=request.getParameter("list");
		if(strPage==null){strPage="1"; }
		int curPage=Integer.parseInt(strPage);
		String fs=request.getParameter("fs");
		String fi=request.getParameter("fi");
		
		BoardDTO dto=dao.boardHotData(Integer.parseInt(strNo));
		
		request.setAttribute("page", curPage);
		request.setAttribute("dto", dto);
		request.setAttribute("plist", plist);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		return "humor/content.jsp?page="+curPage+"&no="+strNo+"&list="+plist+"&fs="+fs+"&fi"+fi;
	}

}
