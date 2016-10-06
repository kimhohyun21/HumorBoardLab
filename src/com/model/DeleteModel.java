package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("EUC-KR");
		String no=request.getParameter("no");
		String page=request.getParameter("page");
		String plist=request.getParameter("list");
		String fs=request.getParameter("fs");
		String fi=request.getParameter("fi");
		
		request.setAttribute("no", no);
		request.setAttribute("page", page);
		request.setAttribute("plist", plist);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		return "humor/delete.jsp";
	}

}
