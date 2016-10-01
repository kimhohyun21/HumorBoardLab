package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String plist=request.getParameter("list");
		request.setAttribute("plist", plist);
		
		return "humor/insert.jsp";
	}

}
