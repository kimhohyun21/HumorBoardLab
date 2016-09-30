package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo=request.getParameter("no");
		String strPage=request.getParameter("page");
		request.setAttribute("no", strNo);
		request.setAttribute("page", strPage);
		
		return "humor/reply.jsp";
	}
	
}
