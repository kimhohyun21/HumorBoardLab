package com.model;

import javax.servlet.http.*;
import com.dao.*;

public class ContentModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strNo = request.getParameter("no");
		String strPage = request.getParameter("page");
		
		BoardDAO dao = new BoardDAO();
		//boardContentData�� String ���� Int ������ ��ȯ
		BoardDTO dto = dao.boardContentData(Integer.parseInt(strNo));
		
		request.setAttribute("page", strPage);
		request.setAttribute("dto", dto);
		
		return "humor/content.jsp";
	}
}
