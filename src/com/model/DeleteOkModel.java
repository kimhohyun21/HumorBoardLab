package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

public class DeleteOkModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		String plist = request.getParameter("list");
		
		//DB ¿¬µ¿
		BoardDAO dao = new BoardDAO();
		Boolean bCheck=dao.boardDelete(Integer.parseInt(no),pwd);		
		
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", page);
		request.setAttribute("plist", plist);
		
		return "humor/delete_ok.jsp";
	}
	
	
	
}
