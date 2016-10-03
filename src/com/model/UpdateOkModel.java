package com.model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import java.util.*;

public class UpdateOkModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String plist=request.getParameter("list");
		String strPage=request.getParameter("page");
		String strNo=request.getParameter("no");
		int no=Integer.parseInt(strNo);
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String fs=request.getParameter("fs");
		String fi=request.getParameter("fi");
		
		BoardDAO dao=new BoardDAO();
		BoardDTO dto=new BoardDTO();
		
		dto.setNo(no);
		dto.setName(name);
		dto.setSubject(subject);	
		dto.setContent(content);
		dto.setPwd(pwd);	
		
		boolean bCheck=dao.boardUpdate(dto);
		
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("page", strPage);
		request.setAttribute("plist", plist);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		return "humor/update_ok.jsp";
	}

}
