package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

public class DeleteOkModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("EUC-KR");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		String plist = request.getParameter("list");
		String fs=request.getParameter("fs");
		String fi=request.getParameter("fi");
		
		//DB ����
		BoardDAO dao = new BoardDAO();
		Boolean bCheck=dao.boardDelete(Integer.parseInt(no),pwd);		
		
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", page);
		request.setAttribute("plist", plist);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		return "humor/delete_ok.jsp";
	}
	
	
	
}
