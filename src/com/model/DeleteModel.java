package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String no=request.getParameter("no");
		String page=request.getParameter("page");
		String plist=request.getParameter("list");
		request.setAttribute("no", no);
		request.setAttribute("page", page);
		request.setAttribute("plist", plist);
		
		return "humor/delete.jsp";
	}

}
