package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardDTO;

public class HotModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao=new BoardDAO();
		
		String strNo=request.getParameter("no");
		String strPage=request.getParameter("page");
		String plist=request.getParameter("list");
		if(strPage==null){strPage="1"; }
		int curPage=Integer.parseInt(strPage);
		
		
		BoardDTO dto=dao.boardHotData(Integer.parseInt(strNo));
		
		
		request.setAttribute("page", curPage);
		request.setAttribute("dto", dto);
		request.setAttribute("plist", plist);
		
		return "humor/content.jsp?page="+curPage+"&no="+strNo+"&list="+plist;
	}

}
